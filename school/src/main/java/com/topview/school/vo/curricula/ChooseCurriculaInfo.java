package com.topview.school.vo.curricula;

/**用于提示选课时是否又现有任课老师
 * @author dr
 *
 */
public class ChooseCurriculaInfo {
		private String clazzId;//课程所在的班级id
		private String clazzName;//班级名称
		private String gradeId;//班级所属年级Id
		private String gradeName;//班级所属年级名称
		private String sortName;//班级简称
		private String teacherId;//课程对应的教师Id
		private String curriculaId;//课程id
		private String curriculaName;//课程名称
		private String teacherName;//教师名称
		public String getClazzId() {
			return clazzId;
		}
		public void setClazzId(String clazzId) {
			this.clazzId = clazzId;
		}
		public String getTeacherId() {
			return teacherId;
		}
		public void setTeacherId(String teacherId) {
			this.teacherId = teacherId;
		}
		public String getCurriculaId() {
			return curriculaId;
		}
		public void setCurriculaId(String curriculaId) {
			this.curriculaId = curriculaId;
		}
		public String getCurriculaName() {
			return curriculaName;
		}
		public void setCurriculaName(String curriculaName) {
			this.curriculaName = curriculaName;
		}
		public String getTeacherName() {
			return teacherName;
		}
		public void setTeacherName(String teacherName) {
			this.teacherName = teacherName;
		}
		public String getClazzName() {
			return clazzName;
		}
		public void setClazzName(String clazzName) {
			this.clazzName = clazzName;
		}
		public String getGradeId() {
			return gradeId;
		}
		public void setGradeId(String gradeId) {
			this.gradeId = gradeId;
		}
		public String getGradeName() {
			return gradeName;
		}
		public void setGradeName(String gradeName) {
			this.gradeName = gradeName;
		}
		public String getSortName() {
			return sortName;
		}
		public void setSortName(String sortName) {
			this.sortName = sortName;
		}
		

}
