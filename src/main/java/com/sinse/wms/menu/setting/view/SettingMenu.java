package com.sinse.wms.menu.setting.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.sinse.wms.common.Config;
import com.sinse.wms.common.view.content.BaseContentPage;

public class SettingMenu extends BaseContentPage{
	final int WIDTH = Config.CONTENT_BODY_WIDTH - Config.CONTENT_BODY_BORDER_WIDTH * 2-20;
	final int HEIGHT = Config.CONTENT_BODY_HEIGHT - Config.CONTENT_BODY_BORDER_HEIGHT * 2-30;
	
	JLabel title;
	//테마 설정
	JPanel p_theme;	
	JComboBox<String> cb_theme;
	
	//언어 및 지역 설정
	JPanel p_locale;
	JComboBox<String> cb_locale;
	
	//알림 및 로그 설정
	JPanel p_log;
	JCheckBox ck_notice;
	JCheckBox ck_log;
	
	String[] themes = {"기본", "Nimbus", "Dark"};
	String[] languages = {"한국어", "English"};
 	
	public SettingMenu() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBorder(BorderFactory.createEmptyBorder(50, 60, 30, 0));
		setBackground(new Color(250, 250, 255));
		setOpaque(false);
		
		//스타일
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		Font titleFont = new Font(Config.APP_DEFAULT_FONT, Font.BOLD, 30);
		Font labelFont = new Font(Config.APP_DEFAULT_FONT, Font.BOLD, 14);
		
		title = new JLabel("환경설정");
		title.setFont(titleFont);
		title.setForeground(Config.PRIMARY_COLOR);
		
		
		// 테마 설정 패널
        p_theme = createSectionPanel("🎨 테마 선택", new Color(240, 245, 250));
        cb_theme = new JComboBox<>(themes);
        JLabel themeLabel = new JLabel("테마를 선택하세요:");
        themeLabel.setFont(labelFont);
        p_theme.add(themeLabel);
        p_theme.add(Box.createRigidArea(new Dimension(10, 0)));
        p_theme.add(cb_theme);

        // 언어 설정 패널
        p_locale = createSectionPanel("🌍 언어 설정", new Color(245, 250, 240));
        cb_locale = new JComboBox<>(languages);
        JLabel localeLabel = new JLabel("언어를 선택하세요:");
        localeLabel.setFont(labelFont);
        p_locale.add(localeLabel);
        p_locale.add(Box.createRigidArea(new Dimension(10, 0)));
        p_locale.add(cb_locale);

        // 알림/로그 설정 패널
        p_log = createSectionPanel("🔔 알림 및 로그 설정", new Color(250, 245, 240));
        ck_notice = new JCheckBox("알림 활성화");
        ck_log = new JCheckBox("로그 기록 활성화");
        ck_notice.setFont(labelFont.deriveFont(Font.PLAIN));
        ck_log.setFont(labelFont.deriveFont(Font.PLAIN));
        p_log.add(ck_notice);
        p_log.add(Box.createVerticalStrut(5));
        p_log.add(ck_log);
        

        // 부착
        add(title);
        add(Box.createVerticalStrut(15));
        add(p_theme);
        add(Box.createVerticalStrut(25));
        add(p_locale);
        add(Box.createVerticalStrut(25));
        add(p_log);

	}
	
	private JPanel createSectionPanel(String title, Color bgColor) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder(title));
        panel.setBackground(bgColor);
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.setMaximumSize(new Dimension(WIDTH, 100));
        return panel;
    }
}
