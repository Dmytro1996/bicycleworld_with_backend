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
const addCommentbtn=document.querySelector('.addComment');
addCommentbtn.addEventListener('click',addComment);
const text=document.querySelector('#commentText');
const name=document.querySelector('#commenterName');
function addComment() {
    if(text.value!='' && name.value!='') {
    localStorage[localStorage.length+1]=name.value+':'+text.value;
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
 para.innerHTML=localStorage[i] ;
 document.querySelector('#comments').appendChild(para);
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


