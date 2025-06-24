package com.sinse.wms.mypage.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import com.sinse.wms.common.view.content.BaseContentPage;

public class Mypage extends BaseContentPage{
	JPanel p_north; // 로그인 현황 조회
	JPanel p_south; // 그리드 패널을 적용할 예정
	

	public Mypage(Color color){
		setBackgroundColor(color);
	
		// TODO("레이아웃 객체 생성 ~ 6/26")
	   Dimension p_north_size = new Dimension(906,300);
	   Dimension p_south_size = new Dimension(906,470);
	   
	   //======= 객체 생성 ======
	   p_north = new JPanel();
	   p_south = new JPanel();
	   
	   
	   add(p_north, BorderLayout.NORTH);
	   add(p_south, BorderLayout.CENTER);
	   
	   setVisible(true);
	   setSize(new Dimension(1200,1000));
	}
	
	
}
