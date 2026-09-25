package jp.co.sss.lms.ct.f02_faq;

import static jp.co.sss.lms.ct.util.WebDriverUtils.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * 結合テスト よくある質問機能
 * ケース04
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース04 よくある質問画面への遷移")
public class Case04 {

	/** 前処理 */
	@BeforeAll
	static void before() {
		createDriver();
	}

	/** 後処理 */
	@AfterAll
	static void after() {
		closeDriver();
	}

	@Test
	@Order(1)
	@DisplayName("テスト01 トップページURLでアクセス")
	void test01() {
		// TODO ここに追加
		// 画面遷移
		goTo("http://localhost:8080/lms");

		// Titleの取得とアサーション
		assertEquals("ログイン | LMS", webDriver.getTitle());

		// エビデンス取得
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(2)
	@DisplayName("テスト02 初回ログイン済みの受講生ユーザーでログイン")
	void test02() {
		// TODO ここに追加
		// ログイン画面を開く
		goTo("http://localhost:8080/lms");

		// ログインIDを入力
		webDriver.findElement(By.id("loginId")).sendKeys("StudentAA01");

		// パスワードを入力
		webDriver.findElement(By.id("password")).sendKeys("StudentAA01");

		// 「ログイン」ボタンを押下
		webDriver.findElement(By.cssSelector("input[type='submit']")).click();

		// 3秒待機
		pageLoadTimeout(3);

		// Titleの取得とアサーション
		assertEquals("コース詳細 | LMS", webDriver.getTitle());

		// エビデンス取得
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(3)
	@DisplayName("テスト03 上部メニューの「ヘルプ」リンクからヘルプ画面に遷移")
	void test03() {
		// TODO ここに追加
		// 「機能」を取得
		By functionMenu = By.xpath(
				"//a[contains(@class,'dropdown-toggle') and normalize-space()='機能']");

		// 3秒待機
		visibilityTimeout(functionMenu, 3);

		// 「機能」を押下
		JavascriptExecutor js = (JavascriptExecutor) webDriver;
		js.executeScript(
				"arguments[0].click();",
				webDriver.findElement(functionMenu));

		// 「機能」が開くまで待機
		new WebDriverWait(webDriver, Duration.ofSeconds(3))
				.until(ExpectedConditions.attributeToBe(
						functionMenu,
						"aria-expanded",
						"true"));

		// エビデンス取得(デバッグ用)
		// getEvidence(new Object() {
		// });

		// 「ヘルプ」を取得
		By helpMenu = By.xpath(
				"//ul[contains(@class,'dropdown-menu')]//a[normalize-space()='ヘルプ']");

		// 3秒待機
		visibilityTimeout(helpMenu, 3);

		// 「ヘルプ」を取得
		var helpElement = webDriver.findElement(helpMenu);

		// 「ヘルプ」を押下
		js.executeScript("arguments[0].click();", helpElement);

		// ヘルプ画面への遷移を待機
		new WebDriverWait(webDriver, Duration.ofSeconds(3))
				.until(ExpectedConditions.titleIs("ヘルプ | LMS"));

		// Titleの取得とアサーション
		assertEquals("ヘルプ | LMS", webDriver.getTitle());

		// エビデンス取得
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(4)
	@DisplayName("テスト04 「よくある質問」リンクからよくある質問画面を別タブに開く")
	void test04() {
		// TODO ここに追加
		// 「よくある質問」を取得
		By faqLink = By.xpath("//a[normalize-space()='よくある質問']");

		// 3秒待機
		visibilityTimeout(faqLink, 3);

		// 「よくある質問」を押下
		//webDriver.findElement(faqLink).click();
		JavascriptExecutor js = (JavascriptExecutor) webDriver;
		js.executeScript("arguments[0].click();",
				webDriver.findElement(faqLink));

		// ウィンドウ/タブの個数が2つになるまで待機
		new WebDriverWait(webDriver, Duration.ofSeconds(3))
				.until(ExpectedConditions.numberOfWindowsToBe(2));

		// すべてのウィンドウハンドルを取得
		Object[] newWindow = webDriver.getWindowHandles().toArray();

		// 新しく開いたタブへ切り替え
		webDriver.switchTo().window((String) newWindow[1]);

		// Titleの取得とアサーション
		assertEquals("よくある質問 | LMS", webDriver.getTitle());

		// エビデンス取得
		getEvidence(new Object() {
		});
	}

}