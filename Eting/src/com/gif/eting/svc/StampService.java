package com.gif.eting.svc;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.gif.eting.dao.InboxDAO;
import com.gif.eting.dao.StampDAO;
import com.gif.eting.dao.StoryDAO;
import com.gif.eting.dto.StampDTO;

/**
 * 스탬프 관련 로직을 처리한다.
 * 
 * @author lifenjoy51
 *
 */
public class StampService {
	
	private Context context;
	@SuppressWarnings("unused")
	private InboxDAO inboxDao;
	@SuppressWarnings("unused")
	private StoryDAO storyDao;
	
	private StampDAO stampDao;	
	
	public StampService(Context context){
		this.context = context;
		this.storyDao = new StoryDAO(this.context);
		this.inboxDao = new InboxDAO(this.context);
		this.stampDao = new StampDAO(this.context);
	}

	/**
	 *  폰에 저장된 스탬프 목록 가져오기
	 * @return
	 */
	public List<StampDTO> getStampList() {
		List<StampDTO> stampList = new ArrayList<StampDTO>();
		stampDao.open();
		stampList = stampDao.getStampList();
		stampDao.close();
		
		return stampList;
	}
	
	/**
	 * 스탬프ID 최대값 가져오기
	 */
	public String getMaxStampId(){
		stampDao.open();
		String maxStampId = stampDao.getMaxStampId();
		stampDao.close();
		return maxStampId;
	}
	
	/**
	 * 스탬프 저장하기
	 */
	public void saveStamps(List<StampDTO> list){
		stampDao.open();
		for(StampDTO stamp : list){
			stampDao.insStamp(stamp);
		}
		stampDao.close();
	}

}
