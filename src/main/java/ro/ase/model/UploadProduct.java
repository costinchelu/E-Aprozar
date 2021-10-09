package ro.ase.model;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
public class UploadProduct {	
	
	private Long idProduct;
	
	@Pattern(regexp="^[a-zA-Z]+$", message="{upload.department.invalid}")
	private String department;
	
	@NotNull(message="{upload.name.null}")
	@NotEmpty(message="{upload.name.empty}")
	private String name;	
	
	@Digits(fraction = 2, integer = 10, message="{upload.price.digits}")
	private double price;
	
	@NotNull(message="{upload.currency.null}")
	private String currency;
	
	@NotNull(message="{upload.availability.null}")
	private String availability;
	
	@NotNull(message="{upload.description.null}")
	@NotEmpty(message="{upload.description.empty}")
	private String description;
	
	@NotNull(message="{upload.specification.null}")
	@NotEmpty(message="{upload.specification.empty}")
	private String specification1;
	
//	@NotNull(message="{upload.specification.null}")
//	@NotEmpty(message="{upload.specification.empty}")
	private String specification2;
	
//	@NotNull(message="{upload.specification.null}")
//	@NotEmpty(message="{upload.specification.empty}")
	private String specification3;	
	
	private String specification4;
	private String specification5;	
	
	@NotNull(message="{upload.image.null}")
	private MultipartFile image;
}
