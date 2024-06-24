function deleteBy(id) {
    const url = '/odontologos/' + id;
    const settings = {
        method: 'DELETE'
    }
    fetch(url, settings)
        .then(() => window.location.reload());
}