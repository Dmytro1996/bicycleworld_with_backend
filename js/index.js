
document.querySelector('.addTextToFirstArticle').addEventListener('click',increaseUsefulness);
function increaseUsefulness() {
    fetch('addData.html')
    .then(response=>response.text())
    .then(html=>document.querySelector('.firstArticle').innerHTML=html);
    document.querySelector('.addTextToFirstArticle').value='Показати менше';
    document.querySelector('.addTextToFirstArticle').removeEventListener('click',increaseUsefulness);
    document.querySelector('.addTextToFirstArticle').addEventListener('click',decreaseUsefulness);
}
function decreaseUsefulness() {
   document.querySelector('.firstArticle').innerHTML='';
   document.querySelector('.addTextToFirstArticle').value='Показати більше';
   document.querySelector('.addTextToFirstArticle').removeEventListener('click',decreaseUsefulness);
   document.querySelector('.addTextToFirstArticle').addEventListener('click',increaseUsefulness);
}
document.querySelector('.addTextToSecondArticle').addEventListener('click',increaseChoice);
function increaseChoice() {
    fetch('addDataToChoice.html')
    .then(response=>response.text())
    .then(html=>document.querySelector('.secondArticle').innerHTML=html);
    document.querySelector('.addTextToSecondArticle').value='Показати менше';
    document.querySelector('.addTextToSecondArticle').removeEventListener('click',increaseChoice);
    document.querySelector('.addTextToSecondArticle').addEventListener('click',decreaseChoice);
}
function decreaseChoice() {
   document.querySelector('.secondArticle').innerHTML='';
   document.querySelector('.addTextToSecondArticle').value='Показати більше';
   document.querySelector('.addTextToSecondArticle').removeEventListener('click',decreaseChoice);
   document.querySelector('.addTextToSecondArticle').addEventListener('click',increaseChoice);
}
