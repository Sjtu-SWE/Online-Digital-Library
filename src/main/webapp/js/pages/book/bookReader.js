
var c = document.getElementById('canvas');
var context = c.getContext('2d');
var backgroundImage = new Image();
var content="在众多的IDE中又许多可以编写javascript，eclipse IDE又显得太大了；notepad++也是不错的选择。而今天我偶尔找到了一款不错的编辑器 Sublime Text 2. 她是一款收费软件，不过可以永久免费试用，在保存的时候偶尔会弹出购买提示 ";
function DrawText() {
    context.fillStyle = "black";
    var x=0, y=0,count=content.length,i=0;

    context.font = "14px sans-serif";
    context.textBaseline = 'top';
    while(count>0){
        var getOne=content.substring(i,i+1);
        if(i%36 ==0){
            x=0;
            y+=18;
        }else{
            x+=15;
        }

        context.fillText(getOne, x, y);
        i++;
        count--;
    }
}
DrawText();