package com.sinse.wms.stock.view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.sinse.wms.common.Config;
import com.sinse.wms.common.view.content.BaseContentPage;

public class StockPage extends BaseContentPage{
	JTable table;
	JScrollPane scroll;
	JPanel p_bottom;
	JButton bt_search;
	JButton bt_excel;
	JButton bt_pdf;
	
	public StockPage(Color color) {
		setBackgroundColor(color);
		
		//생성
		table = new JTable();
		scroll = new JScrollPane(table);
		p_bottom = new JPanel();
		bt_search = new JButton("조회하기");
		bt_excel = new JButton("excel 다운받기");
		bt_pdf = new JButton("pdf 다운받기");
		
		//스타일
		scroll.setPreferredSize(new Dimension(Config.CONTENT_BODY_WIDTH - Config.CONTENT_BODY_BORDER_WIDTH * 2-20,
				(Config.CONTENT_BODY_HEIGHT - Config.CONTENT_BODY_BORDER_HEIGHT * 2-100)));
		scroll.setBackground(Color.darkGray);
		p_bottom.setPreferredSize(new Dimension(Config.CONTENT_BODY_WIDTH - Config.CONTENT_BODY_BORDER_WIDTH * 2-20, 100));
		
		//이벤트 연결
		bt_search.addActionListener(e->{
			System.out.println("조회 버튼 눌렸당");
		});
		
		//조립
		p_bottom.add(bt_search);
		p_bottom.add(bt_excel);
		p_bottom.add(bt_pdf);
		add(scroll, BorderLayout.CENTER);
		add(p_bottom, BorderLayout.SOUTH);
	}
}
