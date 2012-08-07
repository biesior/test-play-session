package controllers;

import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import views.html.index;

public class Application extends Controller {

    private static final int ONE_MINUTE = 60;
    private static final int ONE_HOUR = ONE_MINUTE * 60;
    private static final int ONE_DAY = ONE_HOUR * 24;
    private static final int ONE_YEAR = ONE_DAY * 365;

    private static final int COOKIE_LIFETIME = ONE_YEAR;

    public static Result index() {

        String sessionValue = session("keepme");

        if (sessionValue == null) {
            sessionValue = "(the session value is null)";
        }
        Http.Cookie cookie = request().cookies().get("keepcookie");


        String cookieValue = (cookie == null)
                ? "(the cookie value is null)"
                : cookie.value();


        return ok(index.render(sessionValue, cookieValue));
    }

    public static Result set() {
        session("keepme", "This is message set in session!");
        return redirect(routes.Application.index());
    }

    public static Result destroy() {
        session().remove("keepme");
        return redirect(routes.Application.index());
    }

    public static Result setCookie() {

        response().setCookie("keepcookie", "This is cookie set as... cookie, for " + COOKIE_LIFETIME + " seconds", COOKIE_LIFETIME);
        return redirect(routes.Application.index());
    }

    public static Result destroyCookie() {

        response().discardCookies("keepcookie");
        return redirect(routes.Application.index());
    }

    public static Result setFlash() {

        flash("flashmsg", "That's FlashMessage!!!!");
        return redirect(routes.Application.index());
    }

}