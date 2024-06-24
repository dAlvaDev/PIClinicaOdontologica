function deleteBy(id) {
    const url = '/turnos/' + id;
    const settings = {
        method: 'DELETE'
    }
    fetch(url, settings)
        .then(() => window.location.reload());
}