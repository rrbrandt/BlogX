
import static j2html.TagCreator.body;
import static j2html.TagCreator.h1;
import static j2html.TagCreator.img;



public class Testj2html {

  public static void main(String[] args) {
    
    System.out.println("body = " + body().with(
        h1("Heading!").withClass("example"),
        img().withSrc("img/hello.png")
    ).render());
  }
}
