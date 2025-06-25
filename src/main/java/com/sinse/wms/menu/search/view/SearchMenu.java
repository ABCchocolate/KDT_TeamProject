package com.sinse.wms.menu.search.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class SearchMenu extends JFrame{
	JTextField searchField;
	JButton searchButton;
	JLabel resultLabel;
	JPanel resultPanel;
	
	public SearchMenu() {
		setTitle("검색 창");
        setSize(900, 600);
        setLocationRelativeTo(null); // 화면 중앙에 위치

        // 전체 레이아웃
        setLayout(new BorderLayout());

        // 상단 패널 (검색바 영역)
        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(0x2E36A1));
        topPanel.setPreferredSize(new Dimension(900, 100));
//        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));

        // 검색 입력 필드
        searchField = new JTextField(50);
        searchField.setFont(new Font("SansSerif", Font.PLAIN, 18));
        searchField.setPreferredSize(new Dimension(700, 40));
        searchField.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        
        searchField.setBorder(BorderFactory.createLineBorder(new Color(0x2E36A1), 0));
        searchField.setBorder(BorderFactory.createCompoundBorder(
        		searchField.getBorder(),
        		BorderFactory.createEmptyBorder(10, 20, 10, 20)
        		));

        // 돋보기 버튼
        searchButton = new JButton("🔍");
        searchButton.setFont(new Font("SansSerif", Font.PLAIN, 20));
        searchButton.setPreferredSize(new Dimension(60, 40));
        searchButton.setFocusPainted(false);
        searchButton.setBackground(Color.WHITE);
        searchButton.setBorder(BorderFactory.createEmptyBorder());

        // 검색 이벤트 등록
        searchButton.addActionListener(e -> performSearch());
        searchField.addActionListener(e -> performSearch());

        // 검색바를 포함하는 내부 패널
        JPanel searchBox = new JPanel(new BorderLayout());
        searchBox.setBackground(new Color(0x2E36A1));
        searchBox.setPreferredSize(new Dimension(800, 50));
        searchBox.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        searchBox.add(searchField, BorderLayout.CENTER);
        searchBox.add(searchButton, BorderLayout.EAST);

        topPanel.add(searchBox);
        add(topPanel, BorderLayout.NORTH);

        // 검색 결과 영역
        resultPanel = new JPanel();
        resultPanel.setBackground(Color.WHITE);
        resultLabel = new JLabel("검색어를 입력해주세요.", SwingConstants.CENTER);
        resultLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
        resultPanel.setLayout(new GridBagLayout()); // 가운데 정렬
        resultPanel.add(resultLabel);

        add(resultPanel, BorderLayout.CENTER);

	}
	private void performSearch() {
        String query = searchField.getText().trim();
        if (query.isEmpty()) {
            resultLabel.setText("검색어를 입력해주세요.");
        } else {
            // 실제 검색 로직 구현은 여기서 진행
            resultLabel.setText("검색 결과가 없습니다."); // 예시용
        }
        searchField.setText(""); // 검색 후 입력창 초기화
    }
}
