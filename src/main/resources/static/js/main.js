

// method / function para gerar matrícula aleatória
function gerarMatricula(){
    var texto = "ACA";
    var numeroAleatorio = Math.floor(Math.random() * 1500);
    document.getElementById('matriculaInput').value = (texto + numeroAleatorio);
}