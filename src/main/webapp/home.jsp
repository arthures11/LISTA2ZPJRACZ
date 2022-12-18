<%@ page import="com.bryja.User" %>
<%@ page import="java.util.List" %>
<%@ page import="com.bryja.Post" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.io.*" %>
<%@ page import="com.bryja.Counter" %>
<%@ page import="com.bryja.Odpowiedzi" %>
<!DOCTYPE html>
<html lang="en">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>


<%
    String szukane_pytania = request.getParameter("search");
    String error = request.getParameter("error");
    String liczba_postow = session.getServletContext().getInitParameter("LiczbaPostow");
    int post_amount = Integer.parseInt(liczba_postow);
    User logged = (User)session.getAttribute("user");
    List<Post> posty = new ArrayList<>();
    FileInputStream fi = new FileInputStream(new File("posts.ser"));
    ObjectInputStream oi = new ObjectInputStream(fi);
    try {
        posty = (List<Post>) oi.readObject();
    } catch (ClassNotFoundException e) {
        throw new RuntimeException(e);
    }
    oi.close();
    fi.close();
    //posty.add(new Post(0,"Ile to jest 2+2?","artur", "2022/12/04 00:56:31",0));

    //FileOutputStream f = new FileOutputStream(new File("posts.ser"));
    // ObjectOutputStream o = new ObjectOutputStream(f);
    // o.writeObject(posty);

    // o.close();
    //  f.close();
    int ile = Counter.getActiveSessions();
    String userAgent = request.getHeader("User-Agent");
    if(userAgent.contains("Chrome")){
%><div style="position:fixed;">CHROME<%=" "+ile%></div><%
}else{%> <div style="position:fixed;">MOZILLA</div><%}
%>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>zadanie</title>
    <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/css/Features-Large-Icons-icons.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
</head>

<body>

<nav class="navbar navbar-light navbar-expand-md py-3">
    <div class="container"><a class="navbar-brand d-flex align-items-center" href="#"><span class="bs-icon-sm bs-icon-rounded bs-icon-primary d-flex justify-content-center align-items-center me-2 bs-icon"><svg xmlns="http://www.w3.org/2000/svg" width="1em" height="1em" fill="currentColor" viewBox="0 0 16 16" class="bi bi-bezier">
                        <path fill-rule="evenodd" d="M0 10.5A1.5 1.5 0 0 1 1.5 9h1A1.5 1.5 0 0 1 4 10.5v1A1.5 1.5 0 0 1 2.5 13h-1A1.5 1.5 0 0 1 0 11.5v-1zm1.5-.5a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5h-1zm10.5.5A1.5 1.5 0 0 1 13.5 9h1a1.5 1.5 0 0 1 1.5 1.5v1a1.5 1.5 0 0 1-1.5 1.5h-1a1.5 1.5 0 0 1-1.5-1.5v-1zm1.5-.5a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5h-1zM6 4.5A1.5 1.5 0 0 1 7.5 3h1A1.5 1.5 0 0 1 10 4.5v1A1.5 1.5 0 0 1 8.5 7h-1A1.5 1.5 0 0 1 6 5.5v-1zM7.5 4a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5h-1z"></path>
                        <path d="M6 4.5H1.866a1 1 0 1 0 0 1h2.668A6.517 6.517 0 0 0 1.814 9H2.5c.123 0 .244.015.358.043a5.517 5.517 0 0 1 3.185-3.185A1.503 1.503 0 0 1 6 5.5v-1zm3.957 1.358A1.5 1.5 0 0 0 10 5.5v-1h4.134a1 1 0 1 1 0 1h-2.668a6.517 6.517 0 0 1 2.72 3.5H13.5c-.123 0-.243.015-.358.043a5.517 5.517 0 0 0-3.185-3.185z"></path>
                    </svg></span><span>zapytaj koxa</span></a><button data-bs-toggle="collapse" class="navbar-toggler" data-bs-target="#navcol-4"><span class="visually-hidden">Toggle navigation</span><span class="navbar-toggler-icon"></span></button>
        <div class="collapse navbar-collapse flex-grow-0 order-md-first" id="navcol-4">
            <ul class="navbar-nav me-auto">
                <li class="nav-item"><a class="nav-link active" href="#">ilość pytań w bazie: <%=posty.size()%></a></li>
                <%int sum = 0;
                    for(int i=0;i<posty.size();i++){
                        sum+=posty.get(i).getOdps().size();
                    }

                %>
                <li class="nav-item"><a class="nav-link" href="#">ilość odpowiedzi w bazie: <%=sum%></a></li>
            </ul>
        </div>
        <%if(logged==null){%>
        <div class="d-none d-md-block">                    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
            loguj
        </button>
            <%}%>
            <%if(logged==null){%>
            <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#registerModal">
                rejestruj
            </button>
            <%}%>
            <%if(logged!=null){%>
            <form action="/wyloguj" style="position: relative;display:inline;">
                <div class="modal-footer d-flex justify-content-center">
                    <button type="submit" class="btn btn-primary">wyloguj</button>
                </div>
            </form>
            <%}%>
        </div>
    </div>
</nav>
<div class="container py-4 py-xl-5">
    <div class="row row-cols-1 row-cols-md-2">
        <div class="col-xl-4">
            <div class="text-center text-md-start d-flex flex-column align-items-center align-items-md-start mb-5">
                <h4>Najnowsze pytania</h4>
                <%if(posty.size()>=3){
                for(int i=posty.size()-1;i>posty.size()-4;i--){%>
                <div>
                    <p style="margin-top: 46px;"><%=posty.get(i).getPytanie()%></p><a href="#postnews<%=i%>" data-bs-toggle="modal" data-bs-target="#postnews<%=i%>">Zobacz więcej&nbsp;<svg xmlns="http://www.w3.org/2000/svg" width="1em" height="1em" fill="currentColor" viewBox="0 0 16 16" class="bi bi-arrow-right">
                    <path fill-rule="evenodd" d="M1 8a.5.5 0 0 1 .5-.5h11.793l-3.147-3.146a.5.5 0 0 1 .708-.708l4 4a.5.5 0 0 1 0 .708l-4 4a.5.5 0 0 1-.708-.708L13.293 8.5H1.5A.5.5 0 0 1 1 8z"></path>
                </svg></a>
                </div>

                <div class="modal fade" id="postnews<%=i%>" tabindex="-1" aria-labelledby="postnews" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header text-center">
                                <h4 class="modal-title w-100 font-weight-bold">przegląd posta</h4>
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">zamknij</button>
                            </div>

                            <div class="container py-4 py-xl-5">
                                <div class="row mb-5">
                                    <div class="col-md-8 col-xl-6 text-center mx-auto">
                                        <h2 style="font-size: 18px;text-align: right;">data dodania: <%=posty.get(i).getData()%><br>użytkownik: <%=posty.get(i).getAuthor()%></h2>
                                        <p class="w-lg-50" style="font-size: 18px;text-align: left;"><%=posty.get(i).getId()+1+". "+posty.get(i).getPytanie()%></p>
                                    </div>
                                </div>
                                <% if(posty.get(i).getLiczba_odpowiedzi()>0){
                                    for(int a=0;a<posty.get(i).getOdps().size();a++){%>
                                <div class="row gy-4 row-cols-1 row-cols-md-2 row-cols-xl-3">
                                    <div class="col-12 col-md-12 col-xl-12 col-xxl-12">
                                        <div class="p-4" style="text-align: left;"><span class="badge rounded-pill bg-primary mb-2" style="margin-left: -13px;"><%=posty.get(i).getOdps().get(a).getData()%></span>
                                            <p class ="fw-bold mb-0" style="margin-left: -10px;"><%=posty.get(i).getOdps().get(a).getAuthor()%></p>
                                            <div class="d-flex"><img class="rounded-circle flex-shrink-0 me-3 fit-cover" width="50" height="50" src="https://cdn.bootstrapstudio.io/placeholders/1400x800.png" style="margin-left: -22px;">
                                                <div style="margin-left: -10px;">
                                                    <p class=" mb-0, "><%=posty.get(i).getOdps().get(a).getTresc()%></p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <%}}else{
                                %><div>brak odpowiedzi</div>
                                <%}%>
                            </div>
                            <%if(logged!=null){%>
                            <form action="/dodajodpowiedz">
                                <textarea id="tresc3" name="tresc3" rows="4" cols="50">odpowiedź</textarea>
                                <input type="hidden" id="author3" name="author3" value="<%=logged.getUsername()%>" />
                                <input type="hidden" id="postid3" name="postid3" value="<%=posty.get(i).getId()%>" />
                                <br>
                                <div class="modal-footer d-flex justify-content-center">
                                    <button type="submit" class="btn btn-primary">odpowiedz</button>
                                </div>
                            </form>
                            <%}else{%>
                            <div>aby odpowiedzieć najpierw się zaloguj</div>
                            <%}%>

                        </div>
                    </div>
                </div>
                <%}
                }else{
                    for(int i=posty.size()-1;i>=0;i--){%>
                <div>
                    <p style="margin-top: 46px;"><%=posty.get(i).getPytanie()%></p><a href="#postnews<%=i%>" data-bs-toggle="modal" data-bs-target="#postnews<%=i%>">Zobacz więcej&nbsp;<svg xmlns="http://www.w3.org/2000/svg" width="1em" height="1em" fill="currentColor" viewBox="0 0 16 16" class="bi bi-arrow-right">
                    <path fill-rule="evenodd" d="M1 8a.5.5 0 0 1 .5-.5h11.793l-3.147-3.146a.5.5 0 0 1 .708-.708l4 4a.5.5 0 0 1 0 .708l-4 4a.5.5 0 0 1-.708-.708L13.293 8.5H1.5A.5.5 0 0 1 1 8z"></path>
                </svg></a>
                </div>

                <div class="modal fade" id="postnews<%=i%>" tabindex="-1" aria-labelledby="postnews" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header text-center">
                                <h4 class="modal-title w-100 font-weight-bold">przegląd posta</h4>
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">zamknij</button>
                            </div>

                            <div class="container py-4 py-xl-5">
                                <div class="row mb-5">
                                    <div class="col-md-8 col-xl-6 text-center mx-auto">
                                        <h2 style="font-size: 18px;text-align: right;">data dodania: <%=posty.get(i).getData()%><br>użytkownik: <%=posty.get(i).getAuthor()%></h2>
                                        <p class="w-lg-50" style="font-size: 18px;text-align: left;"><%=posty.get(i).getId()+1+". "+posty.get(i).getPytanie()%></p>
                                    </div>
                                </div>
                                <% if(posty.get(i).getLiczba_odpowiedzi()>0){
                                    for(int a=0;a<posty.get(i).getOdps().size();a++){%>
                                <div class="row gy-4 row-cols-1 row-cols-md-2 row-cols-xl-3">
                                    <div class="col-12 col-md-12 col-xl-12 col-xxl-12">
                                        <div class="p-4" style="text-align: left;"><span class="badge rounded-pill bg-primary mb-2" style="margin-left: -13px;"><%=posty.get(i).getOdps().get(a).getData()%></span>
                                            <p class ="fw-bold mb-0" style="margin-left: -10px;"><%=posty.get(i).getOdps().get(a).getAuthor()%></p>
                                            <div class="d-flex"><img class="rounded-circle flex-shrink-0 me-3 fit-cover" width="50" height="50" src="https://cdn.bootstrapstudio.io/placeholders/1400x800.png" style="margin-left: -22px;">
                                                <div style="margin-left: -10px;">
                                                    <p class=" mb-0, "><%=posty.get(i).getOdps().get(a).getTresc()%></p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <%}}else{
                                %><div>brak odpowiedzi</div>
                                <%}%>
                            </div>
                            <%if(logged!=null){%>
                            <form action="/dodajodpowiedz">
                                <textarea id="tresc2" name="tresc2" rows="4" cols="50">odpowiedź</textarea>
                                <input type="hidden" id="author2" name="author2" value="<%=logged.getUsername()%>" />
                                <input type="hidden" id="postid2" name="postid2" value="<%=posty.get(i).getId()%>" />
                                <br>
                                <div class="modal-footer d-flex justify-content-center">
                                    <button type="submit" class="btn btn-primary">odpowiedz</button>
                                </div>
                            </form>
                            <%}else{%>
                            <div>aby odpowiedzieć najpierw się zaloguj</div>
                            <%}%>

                        </div>
                    </div>
                </div>
                <%}}%>



            </div>
        </div>
        <div class="col-xl-8 d-flex flex-column justify-content-center p-4">
            <center>
                <form action="home.jsp" style="position: relative;display:inline;">
                    <input type="text" name="search" style="margin-top: 5px;position: relative;" />
                    <button type="submit" class="btn btn-primary" type="szukaj">szukaj</button>
                </form>
                <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header text-center">
                                <h4 class="modal-title w-100 font-weight-bold">LOGOWANIE</h4>
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">zamknij</button>
                            </div>
                            <form action="/logowanie" style="position: relative;display:inline;">
                                <div class="modal-body mx-3">
                                    <div class="md-form mb-5">
                                        <i class="fas fa-envelope prefix grey-text"></i>
                                        <input type="text" id="namelog" name="namelog" class="form-control validate">
                                        <label data-error="wrong" data-success="right" for="namelog">login</label>
                                    </div>

                                    <div class="md-form mb-4">
                                        <i class="fas fa-lock prefix grey-text"></i>
                                        <input type="password" id="passwordlog" name="passwordlog" class="form-control validate">
                                        <label data-error="wrong" data-success="right" for="passwordlog">hasło</label>
                                    </div>

                                </div>
                                <div class="modal-footer d-flex justify-content-center">
                                    <button type="submit" class="btn btn-primary">OK</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="modal fade" id="registerModal" tabindex="-1" aria-labelledby="registerModalLabel" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header text-center">
                                <h4 class="modal-title w-100 font-weight-bold">REJESTRACJA</h4>
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">zamknij</button>
                            </div>
                            <form action="/rejestrowanie" style="position: relative;display:inline;">
                                <div class="modal-body mx-3">
                                    <div class="md-form mb-5">
                                        <i class="fas fa-envelope prefix grey-text"></i>
                                        <input type="username" id="name" name="name" class="form-control validate">
                                        <label data-error="wrong" data-success="right" for="name">login</label>
                                    </div>
                                    <div class="md-form mb-4">
                                        <i class="fas fa-lock prefix grey-text"></i>
                                        <input type="password" id="password" name="password" class="form-control validate">
                                        <label data-error="wrong" data-success="right" for="password">hasło</label>
                                    </div>

                                </div>
                                <div class="modal-footer d-flex justify-content-center">
                                    <button type="submit" class="btn btn-primary">OK</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="modal fade" id="errormodal" tabindex="-1" aria-labelledby="errormodal" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header text-center">
                                <h4 class="modal-title w-100 font-weight-bold">BŁĄD</h4>
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">zamknij</button>
                            </div>
                            <div>WPROWADZONO BŁĘDNY LOGIN/HASŁO</div>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="modal fade" id="newpost" tabindex="-1" aria-labelledby="registerModalLabel" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header text-center">
                                <h4 class="modal-title w-100 font-weight-bold">DODAJ PYTANIE</h4>
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">zamknij</button>
                            </div>
                            <%if(logged!=null){%>
                            <form action="/dodajpytanie">
                                <input type="hidden" id="author_pytania" name="author_pytania" value="<%=logged.getUsername()%>" />
                                <div class="modal-body mx-3">
                                    <div class="md-form mb-5">
                                        <i class="fas fa-envelope prefix grey-text"></i>
                                        <input type="text" name="tresc_pytania" id="tresc_pytania" class="form-control validate">
                                        <label data-error="wrong" data-success="right" for="tresc_pytania">pytanie:</label>
                                    </div>

                                </div>
                                <div class="modal-footer d-flex justify-content-center">
                                    <button type="submit" class="btn btn-primary">OK</button>
                                </div>
                            </form>
                            <%}else{%>
                            <div>aby zadać pytanie najpierw się zaloguj</div>
                            <%}%>

                        </div>
                    </div>
                </div>

                <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#newpost">
                    nowy post
                </button>
            </center>
            <div class="container py-4 py-xl-5">
                <%
                    if(szukane_pytania!=null){
                        for(int g=0;g<posty.size();g++){
                            if(!posty.get(g).getPytanie().contains(szukane_pytania)){
                                posty.remove(g);
                            }
                        }
                    }
                    int sajz = posty.size();
                    if(post_amount<posty.size()){
                        sajz = post_amount;
                    }
                    for(int i=0;i< sajz;i++){%>
                <div class="row">
                    <div class="col-md-8 col-xl-6 mx-auto p-4">
                        <div class="d-flex align-items-center align-items-md-start align-items-xl-center">
                            <div class="bs-icon-xl bs-icon-circle bs-icon-primary d-flex flex-shrink-0 justify-content-center align-items-center me-4 d-inline-block bs-icon xl"></div>
                            <div>
                                <h4><%= posty.get(i).getId()+1+". "+posty.get(i).getPytanie()%></h4>
                                <p>Data dodania:&nbsp;<%=posty.get(i).getData()%><br>Liczba udzielonych odpowiedzi:&nbsp;<%=posty.get(i).getLiczba_odpowiedzi()%></p><a href="#post<%=i%>" data-bs-toggle="modal" data-bs-target="#post<%=i%>">Zobacz więcej&nbsp;<svg xmlns="http://www.w3.org/2000/svg" width="1em" height="1em" fill="currentColor" viewBox="0 0 16 16" class="bi bi-arrow-right">
                                <path fill-rule="evenodd" d="M1 8a.5.5 0 0 1 .5-.5h11.793l-3.147-3.146a.5.5 0 0 1 .708-.708l4 4a.5.5 0 0 1 0 .708l-4 4a.5.5 0 0 1-.708-.708L13.293 8.5H1.5A.5.5 0 0 1 1 8z"></path>
                            </svg></a>
                            </div>
                        </div>
                        <hr class="my-3">
                    </div>
                </div>

                <div class="modal fade" id="post<%=i%>" tabindex="-1" aria-labelledby="post" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header text-center">
                                <h4 class="modal-title w-100 font-weight-bold">przegląd posta</h4>
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">zamknij</button>
                            </div>

                            <div class="container py-4 py-xl-5">
                                <div class="row mb-5">
                                    <div class="col-md-8 col-xl-6 text-center mx-auto">
                                        <h2 style="font-size: 18px;text-align: right;">data dodania: <%=posty.get(i).getData()%><br>użytkownik: <%=posty.get(i).getAuthor()%></h2>
                                        <p class="w-lg-50" style="font-size: 18px;text-align: left;"><%=posty.get(i).getId()+1+". "+posty.get(i).getPytanie()%></p>
                                    </div>
                                </div>
                                <% if(posty.get(i).getLiczba_odpowiedzi()>0){
                                    for(int a=0;a<posty.get(i).getOdps().size();a++){%>
                                <div class="row gy-4 row-cols-1 row-cols-md-2 row-cols-xl-3">
                                    <div class="col-12 col-md-12 col-xl-12 col-xxl-12">
                                        <div class="p-4" style="text-align: left;"><span class="badge rounded-pill bg-primary mb-2" style="margin-left: -13px;"><%=posty.get(i).getOdps().get(a).getData()%></span>
                                            <p class ="fw-bold mb-0" style="margin-left: -10px;"><%=posty.get(i).getOdps().get(a).getAuthor()%></p>
                                            <div class="d-flex"><img class="rounded-circle flex-shrink-0 me-3 fit-cover" width="50" height="50" src="https://cdn.bootstrapstudio.io/placeholders/1400x800.png" style="margin-left: -22px;">
                                                <div style="margin-left: -10px;">
                                                    <p class=" mb-0, "><%=posty.get(i).getOdps().get(a).getTresc()%></p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <%}}else{
                                %><div>brak odpowiedzi</div>
                                <%}%>
                            </div>
                            <%if(logged!=null){%>
                            <form action="/dodajodpowiedz">
                                <textarea id="tresc" name="tresc" rows="4" cols="50">odpowiedź</textarea>
                                <input type="hidden" id="author" name="author" value="<%=logged.getUsername()%>" />
                                <input type="hidden" id="postid" name="postid" value="<%=posty.get(i).getId()%>" />
                                <br>
                                <div class="modal-footer d-flex justify-content-center">
                                    <button type="submit" class="btn btn-primary">odpowiedz</button>
                                </div>
                            </form>
                            <%}else{%>
                            <div>aby odpowiedzieć najpierw się zaloguj</div>
                            <%}%>

                        </div>
                    </div>
                </div>

                <%}%>
            </div>

        </div>
    </div>
    <% if(logged!=null){%>
    <footer class="text-center">
        <div class="container text-white py-4 py-lg-5" style="background: #ffffff;">
            <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#myacc">
                Moje konto
            </button>

            <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#myquestions">
                Moje pytania
            </button>


            <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#myanswers">
                Moje odpowiedzi
            </button>
            <p class="text-muted mb-0">Copyright © 2022 Bryja</p>
        </div>
    </footer>
    <div class="modal fade" id="myacc" tabindex="-1" aria-labelledby="myacc" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header text-center">
                    <h4 class="modal-title w-100 font-weight-bold">Twoje dane użytkownika: </h4>
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">zamknij</button>
                </div>
                <div>login: <%=logged.getUsername()%></div>
                <br>
                <div>hasło: <%=logged.getPassword()%></div>
                </form>
            </div>
        </div>
    </div>

    <div class="modal fade" id="myquestions" tabindex="-1" aria-labelledby="myquestions" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header text-center">
                    <h4 class="modal-title w-100 font-weight-bold">Twoje pytania: </h4>
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">zamknij</button>
                </div>

                <%List<Post> koxy = new ArrayList<>();
                    for(int i=0;i<posty.size();i++){
                        if(posty.get(i).getAuthor().equals(logged.getUsername())){
                            koxy.add(posty.get(i));
                        }
                    }
                    for(int i=0;i<koxy.size();i++){
                %>
                <div>pytanie nr. <%=i+1%>: <%=koxy.get(i).getPytanie()%></div>
                <br>
                <%}%>
                </form>
            </div>
        </div>
    </div>


    <div class="modal fade" id="myanswers" tabindex="-1" aria-labelledby="myanswers" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header text-center">
                    <h4 class="modal-title w-100 font-weight-bold">TWOJE ODPOWIEDZI</h4>
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">zamknij</button>
                </div>
                <%List<Odpowiedzi> od = new ArrayList<>();
                    for(int i=0;i<posty.size();i++){
                        for(int j=0;j<posty.get(i).getOdps().size();j++){
                            if(posty.get(i).getOdps().get(j).getAuthor().equals(logged.getUsername())){
                                od.add(posty.get(i).getOdps().get(j));
                            }
                        }

                    }
                %>
                <%
                    for(int i=0;i<od.size();i++){
                        %>
                <div>odpowiedz nr. <%=i+1%>: <%=od.get(i).getTresc()%></div>
                <br>
                <%}%>
                </form>
            </div>
        </div>
    </div>

    <%}%>
</div>
<script src="assets/bootstrap/js/bootstrap.min.js"></script>

<script type="text/javascript">
    if(document.location.href.includes("error=login")) {
        $(window).on('load', function () {
            $('#errormodal').modal('show');
        });
    }
</script>
</body>

</html>