<%@ Page Title="Home Page" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="Default.aspx.cs" Inherits="WebForm._Default" %>

<asp:Content ID="BodyContent" ContentPlaceHolderID="MainContent" runat="server">
    <main>
        <div>
            <asp:TextBox runat="server" ID="x" />
            <br />
            <asp:TextBox runat="server" ID="y" />
            <br />
            <asp:Button runat="server" ID="sum" OnClick="calc_sum" Text="Sum" />
        </div>
        <div>
            <asp:TextBox runat="server" ID="result" />
        </div>
    </main>
</asp:Content>
