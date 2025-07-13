const fileInput = document.getElementById('fileInput');
const profilePic = document.getElementById('profilePic');

fileInput.addEventListener('change', () => {
    const file = fileInput.files[0];

    if (file) {
        const reader = new FileReader();
        reader.onload = e => {
            profilePic.src = e.target.result;
        };
        reader.readAsDataURL(file);
    }
});


const offset = 150;

const botones = [
    { btn: "boton1", target: "section1" },
    { btn: "boton2", target: "section2" },
    { btn: "boton3", target: "section3" }
]

botones.forEach(({ btn, target }) => {
    const boton = document.getElementById(btn);
    const seccion = document.getElementById(target);

    boton.addEventListener("click", (event) => {
        event.preventDefault();

        const top = seccion.getBoundingClientRect().top + window.scrollY - offset;

        window.scrollTo({
            top: top,
            behavior: "smooth"
        });

        botonesDOM.forEach(b => b.classList.remove("active"));
        boton.classList.add("active");
    });
});

const secciones = document.querySelectorAll("section.form-section");
const botonesDOM = document.querySelectorAll(".sidebar a");

window.addEventListener("scroll", () => {
    let sectionVisible = "";

    const limiteSuperior = window.innerHeight / 2;

    secciones.forEach(seccion => {
        const top = seccion.getBoundingClientRect().top;

        if (top <= limiteSuperior && top >= -limiteSuperior) {
            sectionVisible = seccion.getAttribute("id");
        }
    });

    // 🛠️ Si no se detectó ninguna, asumimos que estás arriba de todo (section1)
    if (sectionVisible === "") {
        const scrollTop = window.scrollY || document.documentElement.scrollTop;
        if (scrollTop < 100) {
            sectionVisible = "section1";
        } else {
            return; // Nada visible, no hacer nada
        }
    }

    // Activar botón correspondiente
    botonesDOM.forEach(boton => {
        boton.classList.remove("active");

        const botonIdEsperado = "boton" + sectionVisible.slice(-1);
        if (boton.id === botonIdEsperado) {
            boton.classList.add("active");
        }
    });
});


const inputNombre = document.getElementById("nombreUsuario");
const editarBtn = document.getElementById("editarNombre");
const guardarBtn = document.getElementById("guardarNombre");

const nombreUsuario = "PatoVampiro"
inputNombre.value = nombreUsuario;
inputNombre.dispatchEvent(new Event("input"))

editarBtn.addEventListener('click', () => {
    inputNombre.removeAttribute("readonly");
    inputNombre.focus();
    guardarBtn.removeAttribute("disabled")
})

guardarBtn.addEventListener("click", () => {
    const nuevoNombre = inputNombre.value.trim();

    if (nuevoNombre === "") {
        alert("El nuevo nombre no puede estar vacío");
        return;
    }

    console.log("Nuevo nombre guardado." + nuevoNombre);

    inputNombre.setAttribute("readonly", true)
    guardarBtn.setAttribute("disabled", true)
})