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

