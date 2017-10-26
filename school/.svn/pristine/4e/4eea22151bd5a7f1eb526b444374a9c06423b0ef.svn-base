//导入工具包 require('node_modules里对应模块')
// var gulp = require('gulp'), //本地安装gulp所用到的地方
//     less = require('gulp-less');

var gulp = require('gulp'),
	concat = require("gulp-concat"),
	uglify = require("gulp-uglify"),	
	//项目的路径
	path = "/Users/HuangJianyi/Documents/workspaceJavaEE1/school/src/main/webapp/"; 

gulp.task("process-scripts", function() {
	return gulp.src([

				//工具包所有文件
				path + "app/util/*.js",

				//model层的所有js文件
				path + "app/model/*.js",
				path + "app/model/*/*.js",

				//store层的所有文件
				path + "app/store/*.js",
				path + "app/store/*/*.js",
				path + "app/store/*/*/*.js",

				//view层的所有文件
				path + "app/view/*.js",
				path + "app/view/*/*.js",
				path + "app/view/*/*/*.js"
					
			])
			.pipe(concat("main.js"))
			.pipe(uglify())
			.pipe(gulp.dest(path + "builder"));

});