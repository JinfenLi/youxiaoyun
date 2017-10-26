package com.topview.school.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.topview.school.vo.exam.ScoreInfo;

/**
 * 
 * @ClassName: ScoreInfoUtil
 * @Description: 成绩统计工具类
 * @author Catalyst
 * @date 2016年1月19日
 *
 */
public class ScoreInfoUtil {

	/**
	 * 
	 * @Title: getExcellentRate
	 * @Description: 计算优秀率（分数大于或等于85分）
	 * @param @param scoreList
	 * @param @return 参数
	 * @return Double 返回类型
	 * @throws
	 */
	public static Double getExcellentRate(List<ScoreInfo> scoreList) {

		int exCount = 0;
		int allCount = scoreList.size();
		for (ScoreInfo score : scoreList) {
			if (Float.parseFloat(score.getScore()) >= 85)
				exCount++;
		}
		return (double) exCount / (double) allCount;
	}

	/**
	 * 
	 * @Title: getFailureRate
	 * @Description: 计算不及格率（分数小于60分）
	 * @param @param scoreList
	 * @param @return 参数
	 * @return Double 返回类型
	 * @throws
	 */
	public static Double getFailureRate(List<ScoreInfo> scoreList) {

		int failCount = 0;
		int allCount = scoreList.size();
		for (ScoreInfo score : scoreList) {
			if (Float.parseFloat(score.getScore()) < 60)
				failCount++;
		}
		return (double) failCount / (double) allCount;
	}

	/**
	 * 
	 * @Title: sortASC
	 * @Description: 复制成绩列表并顺序排序返回
	 * @param @param scoreList
	 * @param @return 参数
	 * @return List<ScoreInfo> 返回类型
	 * @throws
	 */
	public static List<ScoreInfo> sortASC(List<ScoreInfo> scoreList) {

		List<ScoreInfo> copy = new ArrayList<ScoreInfo>(scoreList);
		Collections.sort(copy, new Comparator<ScoreInfo>() {
			public int compare(ScoreInfo o1, ScoreInfo o2) {
				return new Float(o1.getScore()).compareTo(new Float(o2
						.getScore()));
			}
		});
		return copy;
	}

	/**
	 * 
	 * @Title: sortDESC
	 * @Description: 复制成绩列表并逆序排序返回
	 * @param @param scoreList
	 * @param @return 参数
	 * @return List<ScoreInfo> 返回类型
	 * @throws
	 */
	public static List<ScoreInfo> sortDESC(List<ScoreInfo> scoreList) {

		List<ScoreInfo> copy = sortASC(scoreList);
		Collections.reverse(copy);
		return copy;
	}

	/**
	 * 
	 * @Title: getLowestScore
	 * @Description: 获取所有最低分的成绩信息
	 * @param @param scoreList
	 * @param @return 参数
	 * @return List<ScoreInfo> 返回类型
	 * @throws
	 */
	public static List<ScoreInfo> getLowestScore(List<ScoreInfo> scoreList) {

		List<ScoreInfo> copy = sortASC(scoreList);
		List<ScoreInfo> lowestList = new ArrayList<ScoreInfo>();
		Float lowest = Float.parseFloat(copy.get(0).getScore());
		for (ScoreInfo score : copy) {
			if (Float.parseFloat(score.getScore()) == lowest)
				lowestList.add(score);
			else
				break;
		}
		return lowestList;
	}

	/**
	 * 
	 * @Title: getHighestScore
	 * @Description: 获取所有最高分的成绩信息
	 * @param @param scoreList
	 * @param @return 参数
	 * @return List<ScoreInfo> 返回类型
	 * @throws
	 */
	public static List<ScoreInfo> getHighestScore(List<ScoreInfo> scoreList) {

		List<ScoreInfo> copy = sortDESC(scoreList);
		List<ScoreInfo> highestList = new ArrayList<ScoreInfo>();
		Float highest = Float.parseFloat(copy.get(0).getScore());
		for (ScoreInfo score : copy) {
			if (Float.parseFloat(score.getScore()) == highest)
				highestList.add(score);
			else
				break;
		}
		return highestList;
	}

	/**
	 * 
	 * @Title: getAverageScore
	 * @Description: 计算平均分
	 * @param @param scoreList
	 * @param @return 参数
	 * @return Float 返回类型
	 * @throws
	 */
	public static Float getAverageScore(List<ScoreInfo> scoreList) {
		Float sum = (float) 0;
		for (ScoreInfo score : scoreList) {
			sum += Float.parseFloat(score.getScore());
		}
		return sum / (float) scoreList.size();
	}

	/**
	 * 
	 * @Title: getScoreLevel
	 * @Description: 获取成绩分数层
	 * @param @param scoreList
	 * @param @return 参数
	 * @return Map<String,List<ScoreInfo>> 返回类型
	 * @throws
	 */
	public static Map<String, List<ScoreInfo>> getScoreLevel(
			List<ScoreInfo> scoreList) {

		List<ScoreInfo> level0 = new ArrayList<ScoreInfo>();
		List<ScoreInfo> level1 = new ArrayList<ScoreInfo>();
		List<ScoreInfo> level2 = new ArrayList<ScoreInfo>();
		List<ScoreInfo> level3 = new ArrayList<ScoreInfo>();
		List<ScoreInfo> level4 = new ArrayList<ScoreInfo>();
		List<ScoreInfo> level5 = new ArrayList<ScoreInfo>();
		List<ScoreInfo> level6 = new ArrayList<ScoreInfo>();
		List<ScoreInfo> level7 = new ArrayList<ScoreInfo>();
		List<ScoreInfo> level8 = new ArrayList<ScoreInfo>();
		List<ScoreInfo> level9 = new ArrayList<ScoreInfo>();
		for (ScoreInfo score : scoreList) {
			int level = (new Float(score.getScore()).intValue() - 1) / 10;
			switch (level) {
			case 0:
				level0.add(score);
				break;
			case 1:
				level1.add(score);
				break;
			case 2:
				level2.add(score);
				break;
			case 3:
				level3.add(score);
				break;
			case 4:
				level4.add(score);
				break;
			case 5:
				level5.add(score);
				break;
			case 6:
				level6.add(score);
				break;
			case 7:
				level7.add(score);
				break;
			case 8:
				level8.add(score);
				break;
			case 9:
				level9.add(score);
				break;
			default:
				break;
			}
		}
		Map<String, List<ScoreInfo>> scoreLevel = new HashMap<String, List<ScoreInfo>>();
		scoreLevel.put("level0", level0);
		scoreLevel.put("level1", level1);
		scoreLevel.put("level2", level2);
		scoreLevel.put("level3", level3);
		scoreLevel.put("level4", level4);
		scoreLevel.put("level5", level5);
		scoreLevel.put("level6", level6);
		scoreLevel.put("level7", level7);
		scoreLevel.put("level8", level8);
		scoreLevel.put("level9", level9);
		return scoreLevel;
	}

	/**
	 * 
	 * @Title: getCountResult
	 * @Description: 统计所有结果并用Map返回
	 * @param @param scoreList
	 * @param @return 参数
	 * @return Map<String,Object> 返回类型
	 * @throws
	 */
	public static Map<String, Object> getCountResult(List<ScoreInfo> scoreList) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (scoreList == null || scoreList.isEmpty()) {
			resultMap.put("success", false);
		} else {
			resultMap.put("success", true);
			resultMap.put("result", scoreList);
			resultMap.put("ASC", sortASC(scoreList));
			resultMap.put("DESC", sortDESC(scoreList));
			resultMap.put("highest", getHighestScore(scoreList));
			resultMap.put("lowest", getLowestScore(scoreList));
			resultMap.put("average", getAverageScore(scoreList));
			resultMap.put("excellentRate", getExcellentRate(scoreList));
			resultMap.put("failureRate", getFailureRate(scoreList));
			resultMap.put("scoreLevel", getScoreLevel(scoreList));
		}
		return resultMap;
	}
	
	public static Map<String, Object> getFanDiagram(List<ScoreInfo> scoreList, String studentId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		if (scoreList == null || scoreList.isEmpty()) {
			result.put("success", false);
			return result;
		} else {
			for(ScoreInfo scoreInfo: scoreList) {
				if(scoreInfo.getStudentId().equals(studentId)) {
					resultMap.put("highest", getHighestScore(scoreList).get(0).getScore());
					resultMap.put("lowest", getLowestScore(scoreList).get(0).getScore());
					resultMap.put("average", getAverageScore(scoreList));
					resultMap.put("value", scoreInfo.getScore());
					result.put("text", scoreInfo.getExamName());
					result.put("score", resultMap);
					result.put("success", true);
				}
			}
		}
		return result;
	}
}
