$(function () {
    var c = document.getElementById('canvas');
    var context = c.getContext('2d');
    var content = $("#chapter-content").html();
    c.height = getCanvasheight();
    function DrawText() {
        context.fillStyle = "black";
        var x = 0, y = 0;

        context.font = "14px sans-serif";
        context.textBaseline = 'top';
        var lines = content.split("\n");
        for (var index = 0; index < lines.length; index++) {
            var line = lines[index],
                count = count = line.length,
                i = 0;
            while (count > 0) {
                var getOne = line.substring(i, i + 1);
                if (i % 60 == 0) {
                    x = 0;
                    y += 18;
                } else {
                    x += 15;
                }

                context.fillText(getOne, x, y);
                i++;
                count--;
            }
            y += 18;
        }
    }

    function getCanvasheight() {
        var y = 0;
        var lines = content.split("\n");
        for (var index = 0; index < lines.length; index++) {
            var line = lines[index],
                count = count = line.length,
                i = 0;
            while (count > 0) {
                if (i % 60 == 0) {
                    y += 18;
                }
                i++;
                count--;
            }
            y += 18;
        }
        return y;
    }

    DrawText();
})
