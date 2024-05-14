const { Builder, Browser, By, Key, until } = require("selenium-webdriver");

const main = async () => {
  try {
    const driver = await new Builder().forBrowser(Browser.CHROME).build();
    await driver.get("http://127.0.0.1:8080/login");
  } catch (error) {
    console.error(error);
  }
};
main();
