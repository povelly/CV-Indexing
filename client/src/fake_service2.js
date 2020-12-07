
function queryf(formData) {
    const res = new Promise((resolve) =>{
        resolve([{
            "tel": "0601010101",
            "nom": formData,
            "prenom":"Quentin",
            "mail":"quentinp34@laposte.net",
            "git":"github"
            },
            {
                "tel": "0601010101",
                "nom": formData,
                "prenom":"Quentin",
                "mail":"quentinp34@laposte.net",
                "git":"github"
                }
        ])
    })
    return res;
} 

/*function queryf(formData) {
    const res = [{
            "tel": "0601010101",
            "nom": formData,
            "prenom":"Quentin",
            "mail":"quentinp34@laposte.net",
            "git":"github"
            },
            {
                "tel": "0601010101",
                "nom": formData,
                "prenom":"Quentin",
                "mail":"quentinp34@laposte.net",
                "git":"github"
                }
        ];
    return res;
}*/

export { queryf }