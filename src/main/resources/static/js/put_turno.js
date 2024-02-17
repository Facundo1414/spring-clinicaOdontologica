window.addEventListener('load', function () {

    const formulario = document.querySelector('#update_turno_form');

    formulario.addEventListener('submit', function (event) {
        let turnoId = document.querySelector('#turno_id').value;
        console.log("id del turno = " + turno_id)
        const formData = {
                    id: document.querySelector('#turno_id').value,
                    fechaTurno: document.querySelector('#fecha').value,
                    pacienteId: document.querySelector('#paciente_id').value,
                    odontologoId: document.querySelector('#odontologo_id').value
                };

        const url = '/turnos';
        const settings = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }
        fetch(url, settings)
            .then(response => response.json())

    })
})

function findBy(id) {
    const url = '/turnos' + "/" + id;
    const settings = {
        method: 'GET'
    }
    fetch(url, settings)
        .then(response => response.json())
        .then(data => {
            let turno = data;
            document.querySelector('#turno_id').value = turno.id;
            document.querySelector('#fecha').value = turno.fechaTurno;
            document.querySelector('#paciente_id').value = turno.pacienteId;
            document.querySelector('#odontologo_id').value = turno.odontologoId;
            //el formulario por default esta oculto y al editar se habilita
            document.querySelector('#div_turno_updating').style.display = "block";
        }).catch(error => {
            alert("Error: " + error);
        })
}