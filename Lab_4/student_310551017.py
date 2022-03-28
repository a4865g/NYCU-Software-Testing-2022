import logging
from pprint import pformat
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.firefox.service import Service
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.remote.webdriver import WebDriver
from webdriver_manager.firefox import GeckoDriverManager

STUDENT_ID = "310551017"

logger = logging.getLogger(__name__)
logging.basicConfig(
    level=logging.INFO,
    format="[WebAppTest][%(asctime)s][%(levelname)s][%(name)s]: %(process)d - %(message)s",
    datefmt="%Y-%m-%d %H:%M:%S",
)


def get_nycu_news(driver: WebDriver):
    driver.get("https://www.nycu.edu.tw/")
    driver.maximize_window()

    driver.find_element(By.XPATH, """//*[@id="menu-1-9942884"]/li[2]/a""").click()
    driver.find_element(By.XPATH, """//*[@id="-tab"]/ul/li[1]/a""").click()

    title_we = driver.find_element(
        By.XPATH, """/html/body/div[1]/div/main/div/div/div/article/header/h1"""
    )
    content_wes = driver.find_elements(
        By.XPATH, """/html/body/div[1]/div/main/div/div/div/article/div/p"""
    )

    logger.info(f"Title: {title_we.text}")
    logger.info(f"Content:\n{pformat([f.text for f in content_wes])}")


def find_google_search(driver: WebDriver, student_id: str):
    driver.switch_to.new_window()
    driver.get("https://www.google.com")
    search_we = WebDriverWait(driver, 10).until(lambda d: d.find_element(By.NAME, "q"))
    search_we.send_keys(student_id)
    search_we.send_keys(Keys.ENTER)

    title_we = WebDriverWait(driver, 10).until(
        lambda d: d.find_element(
            By.XPATH, """//*[@id="rso"]/div[2]/div/div[1]/div/a/h3"""
        )
    )

    logger.info(f"Title: {title_we.text}")


def main():
    try:
        f_driver = webdriver.Firefox(service=Service(GeckoDriverManager().install()))
        get_nycu_news(f_driver)
        find_google_search(f_driver, STUDENT_ID)

    except:
        logger.exception("爬蟲失敗哭哭喔")
        raise

    finally:
        f_driver.quit()


if __name__ == '__main__':
    main()
