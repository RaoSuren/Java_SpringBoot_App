package codinpad.security;

public class JWTAuthenticationFilter extends OncePerRequestFilter
{
   @Autowired
   private UserDetailsService userDetailsService;

   @Autowired
   private JWTTokenHelper jwtTokenHelper;

   @Override
   protected void doFillerInternal(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestToken = request.getHeader("Authorization");

        String username=null;
        String token=null;
        if(requestToken!=null && requestToken.startsWith("Bearer"))
        {
            token = requestToken.substring(7);
           try {
              
              username = this.jwtTokenHelper.getUsernameFromToken(token);
           } catch (IllegalArgumentException e) {
            // TODO: handle exception
             System.out.println("Unable to get Jwt Token");
           } catch(ExpiredJwtException e) {
              System.out.println("Jwt token expired");
           } catch(MalformedJwtException e) {
              System.out.println("Invalid jwt");
           }
           
        }else
        {
           System.out.println("Unable to get Jwt Token");
        }

        if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
           UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
           if(this.jwtTokenHelper)
        }
   }

}