package com.sinse.wms.common.util;

import java.io.File;

/*------------------------------------------------------------------------
 * 유일한 파일 이름 지정
 * 
 * 설명: Excel 및 Pdf 파일 저장 시 저장하려는 디렉토리에 같은 파일이 있으면 덮어씌워지므로
 *        파일 맨 뒤에 숫자를 붙여 유니크한 파일 이름을 생성한다.
 * 사용방법: String fileName = UniqueFileName.getUniqueFileName("파일 디렉토리", "파일 이름", "확장자");
 * ------------------------------------------------------------------------ */
public class UniqueFileName {
	
	public static String getUniqueFileName(String directory, String baseName, String extension) {
		int count = 1;
		File file = new File(directory, baseName +"."+ extension);
		
		while(file.exists()) {		//만약 파일이 존재하면 번호 증가
			file = new File(directory, baseName+"("+count+")"+"."+extension);
			count++;
		}
		return file.getAbsolutePath();
	}
	
	
	/*------------------------------------------------------------------------ 
	 * 유일한 파일 이름 지정(다운로드 폴더고정)
	 * 
	 * 설명: 위와 동일하나 파일을 저장하는 디렉토리를 다운로드 폴더로 고정한다.
	 * 사용방법: String fileName = UniqueFileName.getUniqueDownloadFileName("이름", "확장자"); 
	 *------------------------------------------------------------------------  */
	public static String getUniqueDownloadFileName(String baseName, String extension) {
		//다운로드 폴더에 저장하도록 하여 해당 폴더만 확인
		String downloadPath = System.getProperty("user.home")+File.separator+"Downloads";
		
		int count = 1;
		File file = new File(downloadPath, baseName+"."+ extension);
		
		while(file.exists()) {
			file = new File(downloadPath, baseName+"("+count+")"+"."+extension);
			count++;
		}
		return file.getAbsolutePath();
	}
}
