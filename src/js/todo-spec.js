describe('angularjs homepage todo list', function() {
  it('should add a todo', function() {

    browser.ignoreSynchronization = true;
    browser.get('');
    browser.sleep(2000)

    var test = element(by.xpath('/html/body/div[2]/div/div/div/div[2]/div[1]/a/div'))
    var login = element(by.xpath('//*[@id="login"]'));
    var pass = element(by.xpath('//*[@id="pass"]'));
    var enter = element(by.xpath('//*[@id="log_alt"]/div[2]/input[1]'));
    var play = element(by.xpath('/html/body/div[2]/div/div/div/div[2]/div[1]/a/div'));
    var server62 = element(by.xpath('//*[@id="ServerListModal"]/div[2]/div/div[2]/p[1]/a[2]'));
    var arena = element(by.xpath('//*[@id="ServerListModal"]/div[2]/div/div[2]/p[1]/a[2]'));
    test.click();
    browser.sleep(1000)

    element(by.xpath('//*[@id="regModalTabs"]/li[1]/a')).click();
    login.click();
    login.sendKeys('')
    browser.sleep(500)

    pass.click();
    pass.sendKeys('')
    browser.sleep(500)

    enter.click();
    browser.sleep(1000)

    play.click();
    browser.sleep(1000)

    server62.click();

    browser.sleep(30000)

    /*login.click();
    login.sendKeys('sbely95@mail.ru');

    pass.click();
    pass.sendKeys('d1234567');

    element(by.xpath('//*[@id="log"]/div[2]/input[1]')).click();

    browser.sleep(10000)*/
  });
});