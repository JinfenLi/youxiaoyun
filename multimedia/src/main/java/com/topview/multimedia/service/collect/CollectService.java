/**
 * @Description: 
 * @author chenzufeng
 * @date  2015年7月27日 上午11:23:45 
 * @version V1.0
 */
package com.topview.multimedia.service.collect;

import com.topview.multimedia.vo.CollectInfo;
import com.topview.multimedia.vo.result.CollectInfoResult;

/** 
 * @ClassName: CollectService 
 * @Description: TODO 
 * @author Chenzufeng 1023284613@qq.com
 * @date 2015年7月27日 上午11:23:45 
 *  
 */
public interface CollectService {
	
	public CollectInfoResult collectSave(CollectInfo collect);
	public CollectInfoResult collectDelete(CollectInfo collect);
	public CollectInfoResult collectFind(CollectInfo collect);
	public CollectInfoResult collectFindAll(CollectInfo collect);

}
