<script>
// Flash Toast
$(function() {
  var toastParam = decodeURIComponent(location.hash).match(/flashToast=(success|info|warning|error)\|([^&]+)/);
  if (toastParam) {
    var toastrFunc = toastParam[1],
        toastrMsg = toastParam[2],
        toastTitle = toastrFunc.substring(0, 1).toUpperCase() + toastrFunc.substring(1);
    toastr[toastrFunc](toastrMsg, toastTitle, { progressBar: true });

    var curHash = decodeURIComponent(location.hash);
    var newHash = curHash.substring(0, toastParam.index) + curHash.substring(toastParam.index + toastParam[0].length +1);
    location.hash = newHash;
  }
});
</script>
