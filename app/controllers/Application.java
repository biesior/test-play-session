package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

public class Application extends Controller {

    public static Result index() {

        String sessionValue = session("keepme");

        if (sessionValue == null) {
            sessionValue = "(the session value is null)";
        }

        return ok(index.render(sessionValue));
    }

    public static Result set() {
        session("keepme", "This is message set in session!");
        return redirect(routes.Application.index());
    }

    public static Result destroy() {
        session().remove("keepme");
        return redirect(routes.Application.index());
    }

}