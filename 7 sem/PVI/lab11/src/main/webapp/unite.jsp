<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="wrapper">
    <div>
        <form>
            X: <input name="x-value" id="x-value" value="3"/><br/>
            Y: <input name="y-value" id="y-value" value="7"/><br/>
            N: <input name="n" id="n" value="5"/><br/>
            M: <input name="m" id="m" value="9"/><br/>
            <button type="button" onclick="getValue()">Send</button>
        </form>
    </div>
    <div id="result1">x + y = </div>
    <div id="result2">xml = </div>
    <div id="result3">json = </div>
</div>
<script>
    const isAsync = false;

    function getValue() {
        getValue1();
        getValue2();
        getValue3();
    }

    function getValue1() {
        const req = new XMLHttpRequest();

        const x = document.querySelector("#x-value").value;
        const y = document.querySelector("#y-value").value;
        const z = document.querySelector("#result1");

        const pattern = new RegExp(/\d+/);

        if (pattern.test(x) && pattern.test(y)) {
            if (req) {
                req.open("POST", "<%application.getInitParameter("URL");%>sss_header", isAsync);

                req.onreadystatechange = function () {
                    if (req.readyState === 4) {
                        if (req.status === 200) {
                            z.innerHTML += req.getResponseHeader("Value-Z");
                        } else alert("status = " +
                            req.status + "\n" + req.statusText);
                    }
                };

                req.setRequestHeader("Value-X", x);
                req.setRequestHeader("Value-Y", y);
                req.send();
            }
        } else alert("error");
    }

    function getValue2() {
        const req = new XMLHttpRequest();

        const n = document.querySelector("#n").value;
        const res = document.querySelector("#result2");

        const pattern = new RegExp(/\d+/);

        if (pattern.test(n)) {
            if (req) {
                req.open("POST", "<%application.getInitParameter("URL");%>sss_json", isAsync);

                req.onreadystatechange = function () {
                    if (req.readyState === 4) {
                        if (req.status === 200) {
                            res.innerHTML += JSON.parse(req.response).num.join(", ");
                        } else alert("status = " +
                            req.status + "\n" + req.statusText);
                    }
                };

                req.setRequestHeader("XRand-N", n);
                req.send();
            }
        } else alert("error");
    }

    function getValue3() {
        const req = new XMLHttpRequest();

        const n = document.querySelector("#m").value;
        const res = document.querySelector("#result3");

        const pattern = new RegExp(/\d+/);

        if (pattern.test(n)) {
            if (req) {
                req.open("POST", "<%application.getInitParameter("URL");%>sss_xml", isAsync);

                req.onreadystatechange = function () {
                    if (req.readyState === 4) {
                        if (req.status === 200) {
                            console.log(req.responseXML);
                            res.innerHTML += Array.from(req.responseXML.getElementsByTagName("num"))
                                .map(item => item.textContent).join(", ");
                        } else alert("status = " +
                            req.status + "\n" + req.statusText);
                    }
                };

                req.setRequestHeader("XRand-N", n);
                req.send();
            }
        } else alert("error");
    }
</script>
</body>
</html>
