/*Carrusel*/
var ImgIndex = 0;
showImg();

function showImg() {
       var i;
       var slides = document.getElementsByClassName("Slider__Box__Item");
       for (i = 0; i < slides.length; i++) {
        slides[i].style.display = "none";
       }
       ImgIndex++;
       if(ImgIndex > slides.length) {ImgIndex = 1}
       slides[ImgIndex-1].style.display = "block";
       setTimeout(showImg,8000);
}