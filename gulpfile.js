var gulp = require('gulp');
var bower = require('gulp-bower');

// Run bower for client dependencies resolving
gulp.task('bower', function () {
	return bower();
});