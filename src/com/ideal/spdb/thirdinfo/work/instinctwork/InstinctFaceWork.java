package com.ideal.spdb.thirdinfo.work.instinctwork;

import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;

import com.ideal.spdb.thirdinfo.dao.IEucVehicleExcelDao;
import com.ideal.spdb.thirdinfo.dao.IInstinctDao;
import com.ideal.spdb.thirdinfo.model.ThirdinfoModel;
import com.ideal.spdb.thirdinfo.service.IInstinctService;
import com.ideal.spdb.thirdinfo.work.util.RequestIp;

public class InstinctFaceWork {
	private static final Logger log = Logger.getLogger(InstinctFaceWork.class);
	@Inject
	private IEucVehicleExcelDao vehicleDao;
	@Inject
	IInstinctService instinctService;
	@Inject
	IInstinctDao instinctDao;
	public void everyDayUploadInstinct(){
		log.info("=========InstinctFaceWork.everyDayUploadInstinct()开始执行=============");
		long start = System.currentTimeMillis();
		try {
			String localhostIp = RequestIp.getLocalhostIp();
			String serverIp = this.vehicleDao.getConfigIP("SERVER_IP");
			log.info("localhostIp = " + localhostIp);
			log.info("serverIp = " + serverIp);
			if(localhostIp.equals(serverIp)){
				
				//第一步，同步数据
				
				instinctDao.insertFaceDatas("instinct.insertFaceDatas");
				
				List<ThirdinfoModel> list = null;
				list = instinctDao.getNeedExportInfo("instinct.quaryFaceInfo");
				if(list == null || list.size() == 0){
					log.info("没有需要上传的信息，不生成文件");
					return;
				}
				boolean flag = instinctService.upload(list,8);
				if(flag){
					log.info("文件生成成功..开始修改上传状态");
					for(ThirdinfoModel thirdinfoModel:list){
						instinctDao.updateInstinctFlag(thirdinfoModel, "instinct.updataFaceInfoFlag");
					}
				}else{
					log.info("文件生成失败..不修改上传状态");
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		long end = System.currentTimeMillis();
		log.info("FaceInstinceWork.everyDayUploadInstinct()执行完成..用时：" + (end - start) + "m");
	}
}
