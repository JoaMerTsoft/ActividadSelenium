package test;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.Driver;
import java.util.concurrent.TimeUnit;

public class CasosDePrueba {
    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;
    private String rutaDriver= System.getProperty("user.dir")+"\\src\\test\\resources\\drivers\\chromedriver.exe";
    private String propertyDriver = "webdriver.chrome.driver";

    @AfterMethod
    public void posCondicion(){
        driver.close();
    }

    @BeforeMethod
    public void preCondiciones(){

        System.setProperty(propertyDriver,rutaDriver);
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(40, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver,10);
        js = (JavascriptExecutor) driver;
        driver.navigate().to("https://open.spotify.com/");
        driver.manage().window().maximize();
    }

    //Test 1
    @Test
    public void cp001_btnIniciarSesionFallido() throws InterruptedException {

        //busco el boton de iniciar sesion
        By localizadorBtnIniciarSesion = By.xpath("//button[@data-testid='login-button']");

        //una vez encontrado el boton lo clickeo
        WebElement btnIniciarSesion = driver.findElement(localizadorBtnIniciarSesion);
        btnIniciarSesion.click();

        Thread.sleep(3000);

        //dentro de iniciar sesion, busco el inicio con apple
        By localizadorBtnLogConApple = By.xpath("//button[@data-testid='apple-login']");

        //una vez encontrado el boton de logueo con apple lo clickeo
        WebElement btnlogdeapple = driver.findElement(localizadorBtnLogConApple);
        btnlogdeapple.click();

        Thread.sleep(3000);
        //rellanar el campo en falso
        driver.findElement(By.xpath("//input[@id='account_name_text_field']")).sendKeys("joaquin.mercado@tsoftglobal.com");


        Thread.sleep(3000);

        By localizadorBtnSignIn = By.xpath("//button[@id='sign-in']");
        WebElement btnsignin = driver.findElement(localizadorBtnSignIn);
        btnsignin.click();

        Thread.sleep(3000);
        //colocar contraseña en falso
        driver.findElement(By.xpath("//input[@id='password_text_field']")).sendKeys("123456789");

        Thread.sleep(3000);
        By localizadorBtnConfirmaContraseña = By.xpath("//button[@id='sign-in']");
        WebElement btnconfirmarcontraseña = driver.findElement(localizadorBtnConfirmaContraseña);
        btnsignin.click();

        Thread.sleep(4000);
        Assert.assertEquals(driver.findElement(By.xpath("//p[@id='errMsg']")).getText(),"Contraseña o ID de Apple incorrectos.");
        Thread.sleep(5000);
    }

    //Tests 2
    @Test
    public void CP002BuscarGeneroHipHop() throws InterruptedException {
        By BtnEncontrar = By.xpath("//*[@id=\"main\"]/div/div[2]/nav/div[1]/ul/li[2]/a/span");

        wait.until(ExpectedConditions.presenceOfElementLocated(BtnEncontrar));

        WebElement btnencontrar = driver.findElement(BtnEncontrar);
        btnencontrar.click();

        Thread.sleep(3000);

        By verInputBuscar = By.xpath("//input[@placeholder='¿Qué te apetece escuchar?']");
        driver.findElement(verInputBuscar).sendKeys("Hip-Hop");

        Thread.sleep(3000);
        By Mixgenero = By.xpath("//*[@id=\"searchPage\"]/div/div/section[5]/div[2]/div[1]/div/div[2]/a/div");
        wait.until(ExpectedConditions.presenceOfElementLocated(Mixgenero));

        Thread.sleep(3000);
        WebElement mixGenero = driver.findElement(Mixgenero);
        Assert.assertEquals(mixGenero.getText(),"Hip Hop 100 Hits - Urban rap & R n B anthems inc. Jay Z, A$ap Rocky, Wu-Tang Clan & Nas");
    }

    //Test 3
    @Test
    public void CP003IniciarSesionConCelular() throws InterruptedException {
        By BtnIniciarSesionconcelular = By.xpath("//button[@data-testid='login-button'] ");

        Thread.sleep(3000);
        WebElement btniniciarsesionconcelular = driver.findElement(BtnIniciarSesionconcelular);
        btniniciarsesionconcelular.click();

        Thread.sleep(3000);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@data-testid='phone-login']"))).click();
        driver.findElement(By.xpath("//input[@id='phonelogin-phonenumber']")).sendKeys("29918834411");

        Thread.sleep(3000);
    }
    @Test
    public void CP004IniciarSesionConGoogle() throws InterruptedException {
        By IniciarSesCGoogle = By.xpath("//button[@data-testid='login-button']");

        Thread.sleep(3000);
        WebElement btniniciarsescgoogle = driver.findElement( IniciarSesCGoogle);
        btniniciarsescgoogle.click();

        Thread.sleep(3000);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath ("//button[@data-testid='google-login']"))).click();

        Thread.sleep(3000);
        Assert.assertEquals(driver.getTitle(),"Inicia sesión: Cuentas de Google");

        Thread.sleep(3000);
    }
    @Test
    public void CP005IniciarSesionConFacebook(){

        By BtnIniciarSCFB = By.xpath("//button[@data-testid='login-button']");
        WebElement btniniciarscfb = driver.findElement(BtnIniciarSCFB);
        btniniciarscfb.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@data-testid='facebook-login']"))).click();

        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("joaquin.mercado@tsoftglobal.com");

        driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("2334445555");

        driver.findElement(By.xpath("//*[@id=\"loginbutton\"]")).click();

        By AlertaDeInicioSesion = By.xpath("//*[@id=\"globalContainer\"]/div[3]/div/div/div");

        wait.until(ExpectedConditions.presenceOfElementLocated(AlertaDeInicioSesion));
        WebElement alertadeiniciodesesion = driver.findElement(AlertaDeInicioSesion);

        Assert.assertEquals(alertadeiniciodesesion.getText(), "El correo electrónico que has introducido no está conectado a una cuenta. Encuentra tu cuenta e inicia sesión.");
    }
}

