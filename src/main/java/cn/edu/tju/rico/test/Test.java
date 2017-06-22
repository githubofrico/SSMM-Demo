package cn.edu.tju.rico.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.UrlResource;

public class Test {
	public static void main(String[] args) throws IOException {
		ResourceLoader resloLoader = new DefaultResourceLoader();
		Resource res = resloLoader
				.getResource("https://www.baidu.com/");
		System.out.println(res instanceof UrlResource); // true
		BufferedReader bf = new BufferedReader(new InputStreamReader(res.getInputStream()));
		StringBuilder sb = new StringBuilder();
		String temp = null;
		while ((temp = bf.readLine())!= null) {
			sb.append(temp);
		}
        System.out.println(sb.toString());
        System.out.println("\n-----------------------------\n");
		res = resloLoader.getResource("classpath:test.txt");
		bf = new BufferedReader(new InputStreamReader(res.getInputStream()));
		sb = new StringBuilder();
		temp = null;
		while ((temp = bf.readLine())!= null) {
			sb.append(temp);
		}
        System.out.println(sb.toString());
        System.out.println("\n-----------------------------\n");
		res = resloLoader.getResource("file:C:\\Users\\ricco\\Desktop\\test\\test.txt");
		bf = new BufferedReader(new InputStreamReader(res.getInputStream()));
		sb = new StringBuilder();
		temp = null;
		while ((temp = bf.readLine())!= null) {
			sb.append(temp);
		}
        System.out.println(sb.toString());
	}
}
