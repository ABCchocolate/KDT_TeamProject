package com.sinse.wms.inbound.regist;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import com.sinse.wms.common.util.StringToDate;
import com.sinse.wms.inbound.regist.view.IoRegistPageLayout;
import com.sinse.wms.product.model.IoRequest;
import com.sinse.wms.product.model.Location;
import com.sinse.wms.product.model.Member;
import com.sinse.wms.product.model.Product;
import com.sinse.wms.product.repository.IoRequestDAO;
import com.sinse.wms.product.repository.LocationDAO;
import com.sinse.wms.product.repository.MemberDAO;
import com.sinse.wms.product.repository.ProductDAO;

public class RegistPageController {
	private IoRegistPageLayout view;
	
	public RegistPageController(IoRegistPageLayout view) {
		this.view = view;
		setCombo();
		
		// 등록 버튼 이벤트 구현
		view.getBt_regist().addActionListener(e -> regist());
		
		// 취소 버튼 이벤트 구현
		view.getBt_cancel().addActionListener(e -> cancel());
	}
	
	private void regist() {
		// 유효성 검사, DB처리
		IoRequest ioRequest = new IoRequest();
		IoRequestDAO ioRequestDAO = new IoRequestDAO();
		
		// 콤보박스 선택값 받아오기
		
		Integer.parseInt(view.getArea_registReason().getText());
		Integer.parseInt(view.getArea_remark().getText());
				
		
		
		// ioRequest 모델 인스턴스 세팅하기
		// request_type
		String io_request_type = view.getCb_type().getSelectedItem().toString();
        ioRequest.setIoRequest_type(io_request_type);
        
        // product
        String selectedProductName = view.getCb_product().getSelectedItem().toString();
        ProductDAO productDAO = new ProductDAO();
        Product product = productDAO.findByName(selectedProductName);
        ioRequest.setProduct(product);
        
        // quantity
        int quantity = Integer.parseInt(view.getT_quantity().getText());
        ioRequest.setQuantity(quantity); 
        
        // location
        String selectedLocationName = view.getCb_location().getSelectedItem().toString();
        LocationDAO locationDAO = new LocationDAO();
        Location location = locationDAO.findByName(selectedLocationName); 
        ioRequest.setLocation(location);
        
        // requester
        String selectedRequesterName = view.getCb_requester().getSelectedItem().toString();
        MemberDAO rMemberDAO = new MemberDAO();
        Member requester = rMemberDAO.findByName(selectedRequesterName); 
        ioRequest.setRequest_member_id(requester);
        
        // request_reason
        String selectedApproverName = view.getCb_approver().getSelectedItem().toString();
        MemberDAO aMemberDAO = new MemberDAO();
        Member approver = aMemberDAO.findByName(selectedApproverName) 
        ioRequest.setMember(approver);
        
        /*
        // status : 페이지별 고정값 
        String selectedLocationName = view.getCb_location().getSelectedItem().toString();
        LocationDAO locationDAO = new LocationDAO();
        Location location = locationDAO.findByName(selectedLocationName); 
        ioRequest.setLocation(location);
        */
        
        // Expected_Date 
        String s = view.getT_expected_date().getText();
        StringToDate ;
        ioRequest.setExpected_date(ts);
        
        /*
        // approver 
        String selectedApproverName = view.getCb_approver().getSelectedItem().toString();
        MemberDAO aMemberDAO = new MemberDAO();
        Member approver = aMemberDAO.findByName(selectedApproverName) 
		ioRequest.setMember(approver);
        
        // remark
        String selectedLocationName = view.getCb_location().getSelectedItem().toString();
        LocationDAO locationDAO = new LocationDAO();
        Location location = locationDAO.findByName(selectedLocationName); 
        ioRequest.setLocation(location);
        */
	}
	
	private void cancel() {
		// 화면 닫기
		view.dispose();
		view = null;
	}
	
	private void setCombo() {
		ProductDAO productDAO = new ProductDAO(); 
		LocationDAO locationDAO = new LocationDAO();
		MemberDAO memberDAO = new MemberDAO(); 
		
		List<String> productNames = productDAO.selectProductNames();
		List<String> locationNames = locationDAO.selectLocationNames();
		List<String> memberNames = memberDAO.selectMemberNames();
		
		view.setTypeItems(Arrays.asList("입고", "출고"));
		view.setProductItems(productNames);
		view.setLocationItems(locationNames);
		view.setRequesterItems(memberNames);
		view.setApproverItems(memberNames);

	}
}
