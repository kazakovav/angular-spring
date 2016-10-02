var gulp = require('gulp');
var bower = require('gulp-bower'); // module for resolving module bower dependencies
var concat = require('gulp-concat'); // module for concat files
var uglify = require('gulp-uglify'); // module for minify js files
var cssnano = require('gulp-cssnano'); // minify css files
var inject = require('gulp-inject'); // inject css and js references into index.html
var del = require('del'); // clean files
var gulpif = require('gulp-if'); // need for different environment settings
var mainBowerFiles = require('main-bower-files'); // get list of bower dependencies
// Insert text of  angular template into 'template'' var
// instead reference 'templateUrl' for directives and views
var embedTemplates = require('gulp-angular-embed-templates');


// load environment configuration, please use
// 'gulp {task-name} --env dev' for development environment, settings are in file 'dev-config.js'
// 'gulp {task-name} --env prod' for production environment, settings are in file 'prod-config.js'
var globalConfig = require('./gulp-build-config');

var taskOptions = globalConfig.getConfigKeys();


// params
var src = {
	html: './web-src/*.html',
	js: './web-src/app/**/*.js',
	css: './web-src/css/**/*.css',
	fonts: './web-src/**/*.*'
};

var dist = {
	html: './src/main/webapp',
	js: './src/main/webapp/resources/js',
	jslibs: './src/main/webapp/resources/js/libs',
	css: './src/main/webapp/resources/css',
	fonts: './src/main/webapp/resources/fonts'
};

// Clean dist files and directories
gulp.task('clean', function () {
    return del.sync([dist.html + '/*.html', dist.js, dist.css, dist.fonts]);
});

// Run bower for client dependencies resolving
gulp.task('bower', function () {
	return bower();
});

// concat js libs and minify it
gulp.task('libjs-prepare', ['bower'], function () {
	return gulp.src(mainBowerFiles())
		.pipe(concat('libs.min.js'))
		.pipe(uglify())
		.pipe(gulp.dest(dist.jslibs));
});

// concat css libs and minify it
gulp.task('libcss-prepare', function () {
	return gulp.src(['./web-src/css/bootstrap.css', './web-src/css/bootstrap-theme.css'])
		.pipe(concat('libs.min.css'))
		.pipe(cssnano())
		.pipe(gulp.dest(dist.css));
});

// copy html files to dist, but do not copy templates
gulp.task('html-prepare', function () {
	return gulp.src(src.html).pipe(gulp.dest(dist.html));
});

// concat and minify js src files
gulp.task('js-prepare', function () {
	return gulp.src(src.js)
		.pipe(embedTemplates()) // embed angular templates into js files
		.pipe(gulpif(taskOptions.concat, concat('app.js'))) // concat if there options
		.pipe(gulpif(taskOptions.minify, uglify())) // minify if need
		.pipe(gulp.dest(dist.js));
});

// insert scripts into html pages
gulp.task('html-update', ['libjs-prepare', 'libcss-prepare', 'html-prepare', 'js-prepare'], function () {
	var target = gulp.src(dist.html + '/*.html');
	var sources = gulp.src([dist.jslibs + '/**/*.js', dist.css + '**/*.css', dist.js + '/**/*.js'], { read: false });
	return target.pipe(inject(sources, { relative: true })).pipe(gulp.dest(dist.html));
});

gulp.task('build', ['clean', 'html-update']);
