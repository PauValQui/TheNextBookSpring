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


/*Funcion del menu desplegable*/

const btnMenu= document.getElementById("btnMenu")
const menu=document.getElementById("menu")

function mostrar(){

    const height = 95;
    if(menu.classList.contains("desplegar")){
        menu.classList.remove("desplegar");
        menu.removeAttribute("style");
    }else{
        menu.classList.add("desplegar");
        menu.style.height = height + "%";
    }

    menu.classList.toggle("mostrar");

}

btnMenu.addEventListener('click', mostrar);
