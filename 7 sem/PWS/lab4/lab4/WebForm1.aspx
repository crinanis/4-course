<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="WebForm1.aspx.cs" Inherits="lab4.WebForm1" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>lab4</title>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <script type="module">
        $(document).ready(() => {
            $("#sendButton").on("click", async () => {
                const x = parseInt($("#x").val());
                const y = parseInt($("#y").val());
                const requestData = JSON.stringify({ x, y });

                try {
                    const response = await fetch("Simplex.asmx/AddS", {
                        method: "POST",
                        headers: {
                            "Content-Type": "application/json"
                        },
                        body: requestData
                    });

                    const result = await response.json();
                    $("#result").val(result.d);
                } catch (error) {
                    console.error(error);
                }
            });
        });
    </script>
</head>
<body>
    <form id="form1" runat="server">
        <div>
            <div>
                X:
                <input type="text" id="x" value="46" />
                <br />
                Y:
                <input type="text" id="y" value="4" />
                <br />
                <input type="button" id="sendButton" value="Send" />
            </div>
            <div>
                <input type="text" id="result" readonly />
            </div>
        </div>
    </form>
</body>
</html>
