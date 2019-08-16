document.querySelector('.addTextToUsefulnessArticle').addEventListener('click',increaseUsefulness);
function increaseUsefulness() {
    fetch('addData.html')
    .then(response=>response.text())
    .then(html=>document.querySelector('.firstArticle').innerHTML=html);
    document.querySelector('.addTextToUsefulnessArticle').value='Показати менше';
    document.querySelector('.addTextToUsefulnessArticle').removeEventListener('click',increaseUsefulness);
    document.querySelector('.addTextToUsefulnessArticle').addEventListener('click',decreaseUsefulness);
}
function decreaseUsefulness() {
   document.querySelector('.firstArticle').innerHTML='';
   document.querySelector('.addTextToUsefulnessArticle').value='Показати більше';
   document.querySelector('.addTextToUsefulnessArticle').removeEventListener('click',decreaseUsefulness);
   document.querySelector('.addTextToUsefulnessArticle').addEventListener('click',increaseUsefulness);
}
document.querySelector('.addTextToChoiceArticle').addEventListener('click',increaseChoice);
function increaseChoice() {
    fetch('addDataToChoice.html')
    .then(response=>response.text())
    .then(html=>document.querySelector('.secondArticle').innerHTML=html);
    document.querySelector('.addTextToChoiceArticle').value='Показати менше';
    document.querySelector('.addTextToChoiceArticle').removeEventListener('click',increaseChoice);
    document.querySelector('.addTextToChoiceArticle').addEventListener('click',decreaseChoice);
}
function decreaseChoice() {
   document.querySelector('.secondArticle').innerHTML='';
   document.querySelector('.addTextToChoiceArticle').value='Показати більше';
   document.querySelector('.addTextToChoiceArticle').removeEventListener('click',decreaseChoice);
   document.querySelector('.addTextToChoiceArticle').addEventListener('click',increaseChoice);
}
const addCommentBtn=document.querySelector('.addComment');
addCommentBtn.addEventListener('click',addComment);
const text=document.querySelector('#commentText');
const name=document.querySelector('#commenterName');
function addComment() {
    if(text.value!='' && name.value!='') {
    var num=localStorage.length+1;
    var index='usefullness'+num;
    localStorage[index]=name.value+':'+text.value;
    } else{
        alert('Заповніть всі поля');
    }
    text.value='';
    name.value='';
    }
const showCommentsBtn=document.querySelector('.showComments');
showCommentsBtn.addEventListener('click',show);
function show() {
 for(i=1;i<=localStorage.length;i++) {
 const para=document.createElement('p');
 var index='usefullness'+i;
 if(localStorage[index]!=undefined) {
 para.innerHTML=localStorage[index] ;
 document.querySelector('#comments').appendChild(para);
 }
 }
 showCommentsBtn.value='Сховати коментарі';
 showCommentsBtn.removeEventListener('click',show);
 showCommentsBtn.addEventListener('click',hide);
}
function hide() {
    document.querySelector('#comments').innerHTML='';
    showCommentsBtn.value='Показати коментарі';
    showCommentsBtn.removeEventListener('click',hide);
    showCommentsBtn.addEventListener('click',show);

}
const addCommentToChoiceBtn=document.querySelector('.addCommentToChoice');
addCommentToChoiceBtn.addEventListener('click',addCommentToChoice);
const textOfChoice=document.querySelector('#commentTextOfChoice');
const nameOfChoice=document.querySelector('#commenterNameOfChoice');
function addCommentToChoice() {
    if(textOfChoice.value!='' && nameOfChoice.value!='') {
    var num=localStorage.length+1;
    var index='choice'+num;
    localStorage[index]=nameOfChoice.value+':'+textOfChoice.value;
    } else{
        alert('Заповніть всі поля');
    }
    textOfChoice.value='';
    nameOfChoice.value='';
    }
const showCommentsOfChoiceBtn=document.querySelector('.showCommentsOfChoice');
showCommentsOfChoiceBtn.addEventListener('click',showOfChoice);
function showOfChoice() {
 for(i=1;i<=localStorage.length;i++) {
 const para=document.createElement('p');
 var index='choice'+i;
 if(localStorage[index]!=undefined){
 para.innerHTML=localStorage[index] ;
 document.querySelector('#commentsOfChoice').appendChild(para);
 }
 }
 showCommentsOfChoiceBtn.value='Сховати коментарі';
 showCommentsOfChoiceBtn.removeEventListener('click',showOfChoice);
 showCommentsOfChoiceBtn.addEventListener('click',hideFromChoice);
}
function hideFromChoice() {
    document.querySelector('#commentsOfChoice').innerHTML='';
    showCommentsOfChoiceBtn.value='Показати коментарі';
    showCommentsOfChoiceBtn.removeEventListener('click',hideFromChoice);
    showCommentsOfChoiceBtn.addEventListener('click',showOfChoice);

}


