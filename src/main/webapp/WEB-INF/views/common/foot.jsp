<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<footer class="row-fluid">
    <p>@SJTU 2013</p>
</footer>
<script src="/js/jquery-1.10.2.js"></script>
<script src="/js/bootstrap.js"></script>
<script type="text/javascript" src="/js/pages/common.js"></script>
<script type="text/javascript">
    $(function(){
       $(".recharge").click(function(){
           confirmModel("用户充值","<span class='control-label'  >序列号 :</span><div class='controls-row'><input type='text' placeholder='请输入序列号' id='serialNumber'/></div>","取消","确认",function(){
               var request="serialNumber="+$("#serialNumber").val();
               $.post("/book/recharge.do",request,function(data){
                    modelAlert("用户充值",data.message,"确定");
               });
           });
       });
    });
</script>
