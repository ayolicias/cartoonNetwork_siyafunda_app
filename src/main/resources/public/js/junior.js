$(document).ready(function () {




})

function init() {
    gapi.client.setApiKey("AIzaSyBxEnC28Lg55pIjnSXuKh_uuYgaV4JpkeE");
    gapi.client.load("youtube", "v3", function() {
        // yt api is ready
    });
}