package com.xiang.demo;

import com.xiang.demo.controller.HelloWorldController;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.mail.internet.MimeMessage;
import java.io.File;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private MockMvc mockMvc;

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Autowired
	private JavaMailSender mailSender;

	@Test
	public void contextLoads() {


	}

	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(new HelloWorldController()).build();
	}

	@Test
	public void getToHello() throws Exception {
		logger.trace("I am trace log.");
		logger.debug("I am debug log.");
		logger.warn("I am warn log.");
		logger.error("I am error log.");
		System.out.println("test");
		mockMvc.perform(MockMvcRequestBuilders.get("/helloWorld/to_hello").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("i am Spring Boot!")));
	}

	@Test
	public void testRedis() {
		String test = "dfds";
		logger.info("测试redis:{}", test);
		// 保存字符串
		stringRedisTemplate.opsForValue().set("name", "大帅比");
		Assert.assertEquals("大帅比", stringRedisTemplate.opsForValue().get("name"));
	}

	@Test
	public void sendSimpleMail() throws Exception {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("1150241658@qq.com");
		message.setTo("228873501@qq.com");
		message.setSubject("主题：简单邮件");
		message.setText("测试邮件内容!");

		mailSender.send(message);
	}

	@Test
	public void sendInlineMail() throws Exception {

		MimeMessage mimeMessage = mailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		helper.setFrom("jiangcheng.xiang@qq.com");
		helper.setTo("228873501@qq.com");
		helper.setSubject("主题：嵌入静态资源");
		helper.setText("<html><body><img src=\"cid:weixin\" ></body></html>", true);

		FileSystemResource file = new FileSystemResource(new File("weixin.jpg"));
		helper.addInline("weixin", file);

		mailSender.send(mimeMessage);

	}


}