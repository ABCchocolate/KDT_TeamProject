package com.sinse.wms.product.repository;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sinse.wms.common.util.DBManager;
import com.sinse.wms.product.model.IoRequest;
import com.sinse.wms.product.model.Location;
import com.sinse.wms.product.model.Member;
import com.sinse.wms.product.model.Product;
import com.sinse.wms.product.model.RequestStatus;

public class IoRequestDAO {
    DBManager dbManager = DBManager.getInstance();

    // 전체 입출고 요청 조회
    public List<IoRequest> selectAll() {
        List<IoRequest> list = new ArrayList<>();
        Connection con = dbManager.getConnetion(); // 오타 수정

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM io_request";
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                IoRequest io = new IoRequest();
                io.setIoRequest_id(rs.getInt("io_request_id"));
                io.setIoRequest_type(rs.getString("io_request_type"));
                io.setQuantity(rs.getInt("quantity"));
                io.setRequest_reason(rs.getString("request_reason"));
                io.setRequest_at(rs.getDate("request_at"));
                io.setExpected_date(rs.getDate("expected_date"));
                io.setApproved_at(rs.getDate("approve_at"));
                io.setRemark(rs.getString("remark"));

                Product product = new Product();
                product.setProduct_id(rs.getInt("product_id"));
                io.setProduct(product);

                Location location = new Location();
                location.setLocation_id(rs.getInt("location_id"));
                io.setLocation(location);

                RequestStatus status = new RequestStatus();
                status.setStatus_id(rs.getInt("status_id"));
                io.setStatus(status);

                Member member = new Member();
                member.setMember_id(rs.getInt("approve_member_id"));
                io.setMember(member);

                list.add(io);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbManager.release(pstmt, rs);
          
        }
        return list;
    }
    
    //일별 입출고량 
    public int selectDailyIobound(String ioType) {
    	Connection con = null;
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	int result = 0;
    	
    	con = dbManager.getConnetion();
    	
    	try {
			StringBuffer sql = new StringBuffer();
			sql.append("select ifnull(sum(quantity), 0) as total"
					+ " from io_request where io_request_type=?"
					+ " and date(expected_date)=current_date");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, ioType);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt("total");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(pstmt, rs);
		}
    	return result;
    }
    
    //주별 입출고량
    public int selectWeekIobound(String ioType) {
    	Connection con = null;
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	int result = 0;

    	con = dbManager.getConnetion();
    	
    	try {
			StringBuffer sql = new StringBuffer();
			sql.append("select ifnull(sum(quantity), 0) as total"
					+ " from io_request where io_request_type=?"
					+ " and date(expected_date)"
					+ " between date_sub(current_date, interval(dayofweek(current_date)-2) day)"
					+ " and current_date");
			
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, ioType);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt("total");
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(pstmt, rs);
		}
    	return result;
    }
    
    //월별 입출고량
    public int selectMonthIobound(String ioType) {
    	Connection con = null;
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	int result = 0;

    	con = dbManager.getConnetion();
    	
    	try {
			StringBuffer sql = new StringBuffer();
			sql.append("select ifnull(sum(quantity), 0) as total"
					+ " from io_request where io_request_type=?"
					+ " and year(expected_date) = year(current_date)"
					+ " and month(expected_date) = month(current_date)");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, ioType);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt("total");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(pstmt, rs);
		}
    	return result;
    }
    
    // 입출고량 top 5 항목 가져오기
    public List<IoRequest> selectInOutTop5(String ioType){
    	Connection con = null;
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	List<IoRequest> list = new ArrayList<>();
    	
    	con = dbManager.getConnetion();
    	
    	try {
			StringBuffer sql = new StringBuffer();
			sql.append("select p.product_name, sum(i.quantity) from"
					+ " io_request i inner join product p on i.product_id = p.product_id"
					+ " where i.io_request_type = ?"
					+ " group by product_name order by sum(quantity) desc limit 5");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, ioType);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				IoRequest io = new IoRequest();
				io.setQuantity(rs.getInt("sum(i.quantity)"));

				Product product = new Product();
				product.setProduct_name(rs.getString("p.product_name"));
				
				io.setProduct(product);
				
				list.add(io);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(pstmt, rs);
		}
    	
    	return list;
    }
    
    //카테고리 별 출고 비율(백분율) 가져오기
    public List<Map<String, Double>> selectCategoryQuantityPercent(){
    	Connection con = null;
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	List<Map<String, Double>> list = new ArrayList<>();
    	
    	con = dbManager.getConnetion();
    	
    	try {
			StringBuffer sql = new StringBuffer();
			sql.append("select c.category_name, sum(i.quantity) as total_quantity,"
					+ " round(sum(i.quantity)/sum(sum(i.quantity)) over()*100, 2) as quantity_ratio"
					+ " from io_request i join product p on i.product_id = p.product_id"
					+ " join category c on p.category_id = c.category_id"
					+ " where i.io_request_type = '출고'"
					+ " group by c.category_name"
					+ " order by quantity_ratio desc");
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Map<String, Double> map = new HashMap<>();
				map.put(rs.getString("c.category_name"), rs.getDouble("quantity_ratio"));
				list.add(map);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(pstmt, rs);
		}
    	return list;
    }

    // 입출고 요청 등록
    public void insert(IoRequest io) {
        Connection con = dbManager.getConnetion();
        PreparedStatement pstmt = null;

        try {
            String sql = "INSERT INTO io_request (iorequest_type, product_id, quantity, location_id, request_member_id, request_reason, status_id, request_at, expected_date, approve_member_id, approve_at, remark) "
                       + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, io.getIoRequest_type());
            pstmt.setInt(2, io.getProduct().getProduct_id());
            pstmt.setInt(3, io.getQuantity());
            pstmt.setInt(4, io.getLocation().getLocation_id());
            pstmt.setInt(5, io.getRequest_member_id().getMember_id()); // Member 객체에서 id 추출
            pstmt.setString(6, io.getRequest_reason());
            pstmt.setInt(7, io.getStatus().getStatus_id());
            pstmt.setDate(8, io.getRequest_at());
            pstmt.setDate(9, io.getExpected_date());
            pstmt.setInt(10, io.getMember().getMember_id());
            pstmt.setDate(11, io.getApproved_at());
            pstmt.setString(12, io.getRemark());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbManager.release(pstmt);
            
        }
    }

    // 입출고 요청 수정
    public void update(IoRequest io) {
        Connection con = dbManager.getConnetion();
        PreparedStatement pstmt = null;

        try {
            String sql = "UPDATE io_request SET iorequest_type=?, product_id=?, quantity=?, location_id=?, request_member_id=?, request_reason=?, status_id=?, request_at=?, expected_date=?, approve_member_id=?, approve_at=?, remark=? "
                       + "WHERE iorequest_id=?";

            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, io.getIoRequest_type());
            pstmt.setInt(2, io.getProduct().getProduct_id());
            pstmt.setInt(3, io.getQuantity());
            pstmt.setInt(4, io.getLocation().getLocation_id());
            pstmt.setInt(5, io.getRequest_member_id().getMember_id());
            pstmt.setString(6, io.getRequest_reason());
            pstmt.setInt(7, io.getStatus().getStatus_id());
            pstmt.setDate(8, io.getRequest_at());
            pstmt.setDate(9, io.getExpected_date());
            pstmt.setInt(10, io.getMember().getMember_id());
            pstmt.setDate(11, io.getApproved_at());
            pstmt.setString(12, io.getRemark());
            pstmt.setInt(13, io.getIoRequest_id());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbManager.release(pstmt);
           
        }
    }

    // 입출고 요청 삭제
    public void delete(int iorequestId) {
        Connection con = dbManager.getConnetion();
        PreparedStatement pstmt = null;

        try {
            String sql = "DELETE FROM io_request WHERE iorequest_id = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, iorequestId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbManager.release(pstmt);
           
        }
    }
    
    public List<IoRequest> selectByFilter(String ioRequestType, String statusName, List<String> filterValues) {
        List<IoRequest> list = new ArrayList<>();
        Connection con = dbManager.getConnetion();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            StringBuilder sql = new StringBuilder(
                "SELECT * FROM io_request WHERE io_request_type = ? AND status_id = (SELECT status_id FROM request_status WHERE status_name = ?)"
            );

            // 실제 컬럼명 배열
            String[] columnNames = {
                "product_id", "request_reason", "request_member_id", 
                "location_id", "expected_date", "approve_member_id"
            };

            // 필터 조건 붙이기
            for (int i = 0; i < filterValues.size(); i++) {
                String value = filterValues.get(i);
                if (value != null && !value.isEmpty()) {
                    sql.append(" AND ").append(columnNames[i]).append(" = ?");
                }
            }

            pstmt = con.prepareStatement(sql.toString());

            int index = 1;
            pstmt.setString(index++, ioRequestType);
            pstmt.setString(index++, statusName);

            // 필터 값 파라미터 설정
            for (int i = 0; i < filterValues.size(); i++) {
                String value = filterValues.get(i);
                if (value != null && !value.isEmpty()) {
                    pstmt.setString(index++, value);
                }
            }

            rs = pstmt.executeQuery();

            while (rs.next()) {
                IoRequest io = new IoRequest();
                io.setIoRequest_id(rs.getInt("io_request_id"));
                io.setIoRequest_type(rs.getString("io_request_type"));
                io.setQuantity(rs.getInt("quantity"));
                io.setRequest_reason(rs.getString("request_reason"));
                io.setRequest_at(rs.getDate("request_at"));
                io.setExpected_date(rs.getDate("expected_date"));
                io.setApproved_at(rs.getDate("approved_at"));
                io.setRemark(rs.getString("remark"));

                Product product = new Product();
                product.setProduct_id(rs.getInt("product_id"));
                io.setProduct(product);

                Location location = new Location();
                location.setLocation_id(rs.getInt("location_id"));
                io.setLocation(location);

                RequestStatus status = new RequestStatus();
                status.setStatus_id(rs.getInt("status_id"));
                io.setStatus(status);

                Member member = new Member();
                member.setMember_id(rs.getInt("approve_member_id"));
                io.setMember(member);

                list.add(io);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbManager.release(pstmt, rs);
        }

        return list;
    }

}
