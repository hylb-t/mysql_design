//当鼠标悬浮时, 显示背景颜色
function showBGColor() {
    //event: 当前发送的事件
    //event.srcElement: 事件源
    if (event && event.srcElement && event.srcElement.tagName == "TD") {
        var td = event.srcElement;
        //td.parentElement 表示获取td的父元素
        var tr = td.parentElement;
        //如果想要通过js代码设置某节点的样式  则需要加上.style
        tr.style.backgroundColor = "navy";

        //tr.cells 表示获取tr中的所有单元格
        var tds = tr.cells;
        for (var i = 0;i <= tds.length;i++) {
            tds[i].style.color="red";
        }
    }
}

//当鼠标离开时  恢复原始样式
function clearBGColor() {
    if (event && event.srcElement && event.srcElement.tagName == "TD") {
        var td = event.srcElement;
        var tr = td.parentElement;
        tr.style.backgroundColor= "#f0f8ff";
        var tds = tr.cells;
        for (var i = 0;i <= tds.length;i++) {
            tds[i].style.color="gray";
        }
    }
}

//当鼠标悬浮在单价单元格时  显示手势
function showHand() {
    if (event && event.srcElement && event.srcElement.tagName == "TD") {
        var td = event.srcElement;
        //cursor: 光标
        td.style.cursor = "hand";
    }
}