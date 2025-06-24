package com.sinse.wms.inbound.regist.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.sinse.wms.common.Config;
import com.sinse.wms.common.view.button.OutLineButton;

public class IoRegistPageLayout extends JDialog {
	private JLabel la_type, la_product, la_location, la_quantity, la_registReason, la_expected_date, la_requester, la_approver, la_remark;
	private JComboBox<String> cb_type, cb_product, cb_location, cb_requester, cb_approver;
	private JTextField t_quantity, t_expected_date;
	private JTextArea area_registReason, area_remark;
	private JScrollPane scroll_registReason, scroll_remark;
	private OutLineButton bt_regist, bt_cancel;
	
	

	public IoRegistPageLayout() {
		Dimension d = new Dimension(120, 30);
		Dimension d2 = new Dimension(150, 30);
		
		la_expected_date = new JLabel("예정 일자");
		la_type = new JLabel("입출고 타입");
		la_product = new JLabel("상품 선택");
		la_location = new JLabel("창고 선택");
		la_quantity = new JLabel("수량 입력");
		la_registReason = new JLabel("입출고 사유");
		la_requester = new JLabel("등록 요청인");
		la_approver = new JLabel("승인 관리인");
		la_remark = new JLabel("비고");
		
		cb_type = new JComboBox<>();
		cb_product = new JComboBox<>();
		cb_location = new JComboBox<>();
		cb_requester = new JComboBox<>();
		cb_approver = new JComboBox<>();
		t_expected_date = new JTextField();
		t_quantity = new JTextField("수량을 입력하세요");
		area_registReason = new JTextArea("사유를 입력하세요");
		area_remark = new JTextArea("비고");

		scroll_registReason = new JScrollPane(area_registReason);
		scroll_remark = new JScrollPane(area_remark);
		bt_regist = new OutLineButton("등록", 107, 30, 5, 1, Config.PRIMARY_COLOR, Color.WHITE);
		bt_cancel = new OutLineButton("취소", 107, 30, 5, 1, Config.PRIMARY_COLOR, Color.WHITE);
		
		la_type.setPreferredSize(d);
		la_product.setPreferredSize(d);
		la_location.setPreferredSize(d);
		la_quantity.setPreferredSize(d);
		la_registReason.setPreferredSize(d);
		la_expected_date.setPreferredSize(d);
		la_requester.setPreferredSize(d);
		la_approver.setPreferredSize(d);		
		la_remark.setPreferredSize(d);		
		cb_type.setPreferredSize(d2);
		cb_product.setPreferredSize(d2);
		cb_location.setPreferredSize(d2);
		cb_requester.setPreferredSize(d2);
		cb_approver.setPreferredSize(d2);
		t_expected_date.setPreferredSize(d2);
		t_quantity.setPreferredSize(d2);
		scroll_registReason.setPreferredSize(new Dimension(150, 120));
		scroll_remark.setPreferredSize(d2);
		
		add(la_expected_date);
		add(t_expected_date);
		add(la_type);
		add(cb_type);
		add(la_product);
		add(cb_product);
		add(la_location);
		add(cb_location);
		add(la_quantity);
		add(t_quantity);
		add(la_registReason);
		add(scroll_registReason);
		add(la_requester);
		add(cb_requester);
		add(la_approver);
		add(cb_approver);
		add(bt_regist);
		add(bt_cancel);
		add(la_remark);
		add(scroll_remark);
		
		setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
		setPreferredSize(new Dimension(320, 450));
		setSize(new Dimension(320, 450));
		setVisible(true);
	}
	
	public void setTypeItems(List<String> items) {
		cb_type.setModel(new DefaultComboBoxModel<>(items.toArray(new String[0])));
	}
	public void setProductItems(List<String> items) {
		cb_product.setModel(new DefaultComboBoxModel<>(items.toArray(new String[0])));
	}
	public void setLocationItems(List<String> items) {
		cb_location.setModel(new DefaultComboBoxModel<>(items.toArray(new String[0])));
	}
	public void setRequesterItems(List<String> items) {
		cb_requester.setModel(new DefaultComboBoxModel<>(items.toArray(new String[0])));
	}
	public void setApproverItems(List<String> items) {
		cb_approver.setModel(new DefaultComboBoxModel<>(items.toArray(new String[0])));
	}
	
	public OutLineButton getBt_regist() {
		return bt_regist;
	}
	
	public OutLineButton getBt_cancel() {
		return bt_cancel;
	}
	
	public JComboBox<String> getCb_type() {
		return cb_type;
	}
	
	public JComboBox<String> getCb_product() {
		return cb_product;
	}
	
	public JComboBox<String> getCb_location() {
		return cb_location;
	}
	
	public JComboBox<String> getCb_requester() {
		return cb_requester;
	}
	
	public JComboBox<String> getCb_approver() {
		return cb_approver;
	}
	
	public JTextField getT_quantity() {
		return t_quantity;
	}
	
	public JTextArea getArea_registReason() {
		return area_registReason;
	}
	
	public JTextField getT_expected_date() {
		return t_expected_date;
	}
	
	public JTextArea getArea_remark() {
		return area_remark;
	}
}
