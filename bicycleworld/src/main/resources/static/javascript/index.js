var btns=document.querySelectorAll(".addTextToArticle");
for(let i=0;i<btns.length;i++){
    btns[i].addEventListener('click',addText);
}
function addText(event){
    var parent=event.target.parentNode;
    //var child=parent.children[0].children[2];
    parent.children[0].children[2].style.display='block';
    parent.children[3].style.display='block';
    parent.removeAttribute("class");
    this.value='Показати меньше';
    this.removeEventListener('click',addText);
    this.addEventListener('click',hideText);
}
function hideText(event){
    var parent=event.target.parentNode;
    parent.children[0].children[2].style.display='none';
    parent.children[3].style.display='none';
    if(parent.children[4].style.display==='block'){
        parent.children[4].style.display='none';
        parent.children[3].children[parent.children.length].value='Показати коментарі';
        parent.children[3].children[parent.children.length].removeEventListener('click',hideComments);
        parent.children[3].children[parent.children.length].addEventListener('click',showComments);
    }
    parent.classList.add('unOpenedArticle');
    this.value='Показати більше';
    this.removeEventListener('click',hideText);
    this.addEventListener('click',addText);
}
var loginMenuOpenerBtn=document.querySelector('#loginMenuOpener');
if(loginMenuOpenerBtn!==null){loginMenuOpenerBtn.addEventListener('click',openDropDownMenu);}
function openDropDownMenu(){
    //window.addEventListener('click',closeDropDownMenu);
    document.querySelector('#dropdownMenu').style.display='block';
    loginMenuOpenerBtn.removeEventListener('click',openDropDownMenu);
    loginMenuOpenerBtn.addEventListener('click',closeDropDownMenu);    
}
function closeDropDownMenu(){
    document.querySelector('#dropdownMenu').style.display='none';
    loginMenuOpenerBtn.removeEventListener('click',closeDropDownMenu);
    //window.removeEventListener('click',closeDropDownMenu);
    loginMenuOpenerBtn.addEventListener('click',openDropDownMenu);
}
let loginBtn=document.querySelector('#loginButton');
if(loginBtn!==null){loginBtn.addEventListener('click',showLoginForm);}
function showLoginForm(){
    document.querySelector("#modal").style.display='block';
    document.querySelector("#loginFormContainer").style.display='block';    
}
let closeBtn=document.querySelector('#close');
closeBtn.addEventListener('click',closeLoginForm);
function closeLoginForm(){
    document.querySelector("#modal").style.display='none';
    document.querySelector("#loginFormContainer").style.display='none';
}
const showCommentsBtns=document.querySelectorAll('.showComments');
for(let i=0;i<showCommentsBtns.length;i++){
    showCommentsBtns[i].addEventListener('click',showComments);
}
//showCommentsBtn.addEventListener('click',showComments);
function showComments(event) {
    let articleDiv=event.target.parentNode.parentNode;
    articleDiv.children[4].style.display='block';
    this.value='Сховати коментарі';
    this.removeEventListener('click',showComments);
    this.addEventListener('click',hideComments);
}
function hideComments(event) {
    let articleDiv=event.target.parentNode.parentNode;
    articleDiv.children[4].style.display='none';    
    this.value='Показати коментарі';
    this.removeEventListener('click',hideComments);
    this.addEventListener('click',showComments);

}
window.onclick=function(event){
    if(event.target===document.querySelector('#modal')){
         closeLoginForm();         
    }
    if(event.target!==loginMenuOpenerBtn){
        if(document.querySelector('#dropdownMenu').style.display==='block'){
             closeDropDownMenu();
         }
    }    
};




