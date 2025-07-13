const data = await fetch("https://tfgnncxxcoycjuwiceby.supabase.co/rest/v1/usuarios?apikey=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InRmZ25uY3h4Y295Y2p1d2ljZWJ5Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDk3ODMxMTcsImV4cCI6MjA2NTM1OTExN30.QGblsb3yJ7jMMC0ArYbY5qkFALtff809Rbgn12be6RI").then(response => response.json());

export { data }