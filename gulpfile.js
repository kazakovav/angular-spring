var gulp = require('gulp');
var bower = require('gulp-bower');
var concat = require('gulp-concat');
var uglify = require('gulp-uglify');
var inject = require('gulp-inject');
var del = require('del');
var mainBowerFiles = require('main-bower-files');
var embedTemplates = require('gulp-angular-embed-templates');

var globalConfig = require('./gulp-build-config');


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
	css: './src/main/webapp/resources/css',
	fonts: './src/main/webapp/resources/fonts'
};

gulp.task('test-log', function () {
	console.log('environment: ' + globalConfig.environment);
	console.log(`base dir: ${dist.baseDir}`);
	console.log(`concat is: ${globalConfig.getConfigKeys().concat}`)
});

// Clean src files
gulp.task('clean', function () {
    return del.sync([`${dist.html}/*.html`, `${dist.js}`, `${dist.css}`, `${dist.fonts}`]);
});

// Run bower for client dependencies resolving
gulp.task('bower', function () {
	return bower();
});

// concat libs and minify it
gulp.task('lib-prepare', ['bower'], function () {
	return gulp.src(mainBowerFiles())
		.pipe(concat('libs.min.js'))
		.pipe(uglify())
		.pipe(gulp.dest(dist.js));
});

// insert scripts into html pages
gulp.task('index', function () {
	var target = gulp.src('./web-src/index.html');
	// It's not necessary to read the files (will speed up things), we're only after their paths: 
	var sources = gulp.src(['./web-src/**/*.js', './web-src/**/*.css'], { read: false });

	return target.pipe(inject(sources, { relative: true })).pipe(gulp.dest('web-src'));
});



gulp.task('bower-copy', ['bower'], function () {
    // mainBowerFiles is used as a src for the task,
    // usually you pipe stuff through a task
    return gulp.src(mainBowerFiles())
        // Then pipe it to wanted directory, I use
        // dist/lib but it could be anything really
        .pipe(gulp.dest('./web-src/dist/lib'))
});

gulp.task('embed-template', function () {
    gulp.src('./web-src/app/**/*.js')
        .pipe(embedTemplates())
        .pipe(gulp.dest('./dist/js'));
});