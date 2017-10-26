//导入工具包 require('node_modules里对应模块')
// var gulp = require('gulp'), //本地安装gulp所用到的地方
//     less = require('gulp-less');

var gulp = require('gulp'),
	concat = require("gulp-concat"),
	uglify = require("gulp-uglify");

gulp.task("process-scripts", function() {
	return gulp.src([

				//工具包所有文件
				"D:/Users/yuge/workspace/school/src/main/webapp/app/util/*.js",

				//model层的所有js文件
				"D:/Users/yuge/workspace/school/src/main/webapp/app/model/*.js",
				"D:/Users/yuge/workspace/school/src/main/webapp/app/model/*/*.js",

				//store层的所有文件
				"D:/Users/yuge/workspace/school/src/main/webapp/app/store/*.js",
				"D:/Users/yuge/workspace/school/src/main/webapp/app/store/*/*.js",
				"D:/Users/yuge/workspace/school/src/main/webapp/app/store/*/*/*.js",

				//view层的所有文件
				"D:/Users/yuge/workspace/school/src/main/webapp/app/view/*.js",
				"D:/Users/yuge/workspace/school/src/main/webapp/app/view/*/*.js",
				"D:/Users/yuge/workspace/school/src/main/webapp/app/view/*/*/*.js"
					
			])
			.pipe(concat("main.js"))
			.pipe(uglify())
			.pipe(gulp.dest("./"));

});