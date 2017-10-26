
//把ux目录下的文件加载进来
Ext.Loader.setConfig({
    enabled: true,
    paths: {
        //Ext: '.',
        'Ext.ux': 'ux'
        //'Packt.util': 'app/util'
    }
 });

//程序的入口
Ext.application({
    name: 'School', //使用的命名空间为School
    enableQuickTip: true, //初始化工具栏提示功能
    requires: [
        "School.view.Login",
        "School.util.SessionMonitor" //超时处理
    ],
    controllers: [
      	"Login",                        //登陆
        "Menu",                        //菜单
        "User",
        "area.AreaMgr",
        "area.SchoolMgr",              //学校管理
        "course.CourseMgr",            //学科管理
        "course.CurriculaMgr",         //课程管理
        "course.SelectCurricula",      //选课管理
        "school.GradeMgr",             //年级管理
        "school.ClassMgr",             //班级管理
        "school.Termmgr",              //学期管理
        "clazz.MyClass",               //我的班级
        "clazz.PostMgr",               //班级帖子
        "clazz.Syllabus",              //课表管理
        "teacher.DepartMgr",           //部门管理
        "teacher.TeacherMgr",          //教师管理
        "exam.ExamMgr",                //考试管理
        "exam.ScoreMgr",               //成绩管理 
        "multimedia.AlbumMgr",         //相册管理
        "multimedia.PhotoMgr",         //照片管理
        "multimedia.LibMgr",           //视频库管理
        "multimedia.VideoMgr",         //视频管理
        "multimedia.CheckPhoto",       
        "school.SchoolSummy",
        "news.NewsMgr",               //新闻管理
        "news.NewsPublish",
        'teacher.Position',             //职位管理
        'clazz.Health',		             //健康管理
        'push.Push'	,	                //消息推送
        "push.MsgList",
        'clazz.Appraise',
        'auth.Role',
        'auth.Module',
        'Learning_Center',	            //学习园地
        'clazz.Getstudentmgr',          //班级管理 
        'clazz.FileShare',                //文件共享
        'exam.SeeOtherGradesmgr',          //查看其它考试
        'area.Getloginmgr',             //查看登陆情况
        'area.SchoolLogin',
        'exam.GetOtherCurriculaMgr'
    ],

   //初始化函数
    init: function() {

      Ext.tip.QuickTipManager.init();
 
      var task = new Ext.util.DelayedTask(function() {
        splashscreen.fadeOut({
            duration: 10,
            remove: true
        });
      
        splashscreen.next().fadeOut({
          duration: 1,
          remove: true,
                listeners: {
                    afteranimate: function(el,startTime,eOpts) {
                      //创建登陆视图
                        Ext.widget("login");
                    }
                }
        });
      });

      task.delay(500);
    },
    
    //文件加载就绪时执行
    launch: function() {
    //初始化工具栏提示功能
      Ext.tip.QuickTipManager.init();
      splashscreen = Ext.getBody().mask("系统正在启动","splashscreen");
      Ext.DomHelper.insertFirst(Ext.query(".x-mask-msg")[0], {
        cls: "x-spalsh-icon"
      }); 
    },
    splashscreen: {}
});
