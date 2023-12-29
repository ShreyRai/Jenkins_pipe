pipeline{
	agent any

	environment {
		username = 'ShreyRai20'
		machine = 'Ubuntu'
	}
	
	parameters {
		string (name: 'Card', defaultValue: 'Visa', description: 'Enter the card name')
		booleanParam (name: 'Minor', defaultValue: false, description:'Are you minor, if yes uncheck the box')
		choice (name: 'Bank', choices: ['SBI', 'HDFC', 'ICICI', 'PNB'], description: 'Select the card name.')
		
	}
	
	stages {
		stage ('Print'){
			steps{
				echo "The procedure will begin shortly"
				sh '''
					echo "The username is ${username}"
					echo "The machine name is ${machine}"
				'''
				echo "This is the build number: ${BUILD_ID}
			}
		}
		stage ('User Value'){
			steps{
				sh '''
					echo "The card is: ${Card}"
					echo "Is Minor: ${Minor}"
					#echo "The Bank name is: ${Bank}"
				'''
			}
		}
		stage ('Permit') {
			input {
				message "The details are saved, want to display them?"
				ok "Yes, show"
			}
		}
		
		stage ('Show'){
			steps {
				sh '''
					echo "${Card} | ${Minor} | ${Bank}"
				'''
			}
		}
		post {
			always{
				echo "The build is complete"
			}
			regression{
				echo "The build failed this time"
			}
			failure{
				echo "The build is a failure"
			}
			success{
				echo "The entire build is a success"
			}
			aborted{
				echo "The build is aborted"
			}
			fixed{
				echo "The build is fixed"
			}
		}
	}
}