const { data, error } = await supabase.auth.signIn({
  email: "gomezrojaskevinmanuel2212@gmail.com",

  password: "Kevin2212,;",
});
if (error) {
  console.error("Error al iniciar sesión:", error);
} else {
  const accessToken = data.session.access_token;

  console.log("Token de acceso:", accessToken);
}
