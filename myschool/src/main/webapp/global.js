/*
*@说明:该文件定义一些全局变量，初始值都为空或则undefined
*	重新赋值操作一般都在ajax的回调函数里
*	这些全局变量的作用是作为ajax请求的参数值
*/

// var schoolGlobal = {
// 	port: "",
// 	userInfo: {},
// 	logoUrl: "",
// 	albumId: "",
// 	openWin: "",
// 	schoolId: "",
// 	schoolName: "",
// 	termId: "",
// 	curriculaId: "",
// 	subjectRec: {},
// 	subjectId: "",
// 	gradeRec: {},
// 	classesId: "",
// 	className: "",
// 	teacherStore: {},
// 	teacherRec: {},
// 	teacherId: "",
// 	chooseCurricula: {},
// 	curriculaTeacher: [],
// 	departTeachers: [],
// 	departId: "",
// 	examRec: {},
// 	viewPagerId: "",
// 	noPassAlbumId: "",
// 	videolibId: "",
// 	examId: "",
// 	selectedSchools: [],
// 	ueditorReady: false
// };

	

 var zyHost = "", 						//服务器的相对路劲
	globalUserInfo = {}, 			 	//当前登陆用户的信息
	zy_logoUrl  = "",           		//logo路径
	zy_albumId = "",					//当 前的相册id,
	zy_openWin = "", 					//当前打开的子窗口
	zy_schoolId = "",					//当前的学校id
	zy_schoolName = "",					//当前学校名称
	zy_termId = "", 					//当前的学期id
 	zy_curriculaId = "", 				//当前的课程id
	zy_subjectRec = {},					//当前的学科数据集
	zy_subjectId = "",  				//当前的学科id
	zy_gradeRec = {},					//当前的年级数据集
	zy_classes = [],					//当前登陆用户的班级
	zy_gradeId = "",					//当前的年级id
	zy_classId = "",					//当前的班级id
	zy_className = "",					//当前的班级名称
	zy_teacherStore = {}, 				//所有教师的缓存数据
	zy_teacherRec = {},					//当前的教师数据集
	zy_teacherId = "",					//当前的教师id
	zy_chooseCurricula = {}, 			//当前的符合添加的学科
	zy_curriculaTeacher = [],			//当前的任课老师集合
	zy_examRec = {},					//当前的考试数据集		
	zy_viewPagerId = "", 				//轮播相册的id
	zy_noPassAlbumId = "", 				//当前班级的未审核相册id
	zy_videolibId = "",					//当前的视频库id
	zy_examId = "",						//当前的考试id
	zy_selectedSchools = [],			//已经选择的学校数组
	zy_ueditorReady = false;			//ueditor是否已经准备就绪









